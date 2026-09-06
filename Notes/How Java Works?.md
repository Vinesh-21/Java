# How Java Works
### A Step-by-Step Walkthrough — from Source Code to Running Program

> Source &nbsp;→&nbsp; Compiler &nbsp;→&nbsp; Bytecode &nbsp;→&nbsp; JVM &nbsp;→&nbsp; Execution

## Contents

- [Glossary — Terms You'll Encounter](#glossary--terms-youll-encounter)
- [§1 The Big Picture — Write Once, Run Anywhere](#1-the-big-picture--write-once-run-anywhere)
- [§1B JDK vs JRE vs JVM](#1b-jdk-vs-jre-vs-jvm)
- [§2 The Running Example](#2-the-running-example)
- [Step 1 — Writing the Source Code](#step-1--writing-the-source-code)
- [Step 2 — Compilation (javac)](#step-2--compilation--source-code--bytecode)
- [Step 2B — From Terminal to Running Program](#step-2b--from-terminal-to-running-program)
- [Step 3 — Class Loading](#step-3--class-loading)
- [Step 4 — Bytecode Verification](#step-4--bytecode-verification)
- [Step 5 — Runtime Data Areas](#step-5--runtime-data-areas)
- [Step 6 — The Execution Engine](#step-6--the-execution-engine--interpreter--jit-compiler)
- [Step 7 — Garbage Collection](#step-7--garbage-collection)
- [Step 8 — Program Output](#step-8--program-output)
- [End-to-End: All Steps Together](#end-to-end-all-steps-together)
- [Test Your Knowledge](#test-your-knowledge)

---

## Glossary — Terms You'll Encounter

Before diving in, here's a quick reference for the terms used throughout this document. You don't need to
memorize these now — come back to this section anytime a term feels unfamiliar.

| Term | What it means |
|---|---|
| **Source code** | The human-readable text you write in a `.java` file, following Java's syntax rules. |
| **JDK (Java Development Kit)** | What you install to write and build Java programs — includes the compiler, the JRE, and other developer tools. |
| **JRE (Java Runtime Environment)** | What's needed to run (but not build) a Java program — the JVM plus the standard libraries. Since Java 11 it's bundled inside the JDK rather than sold separately. |
| **Process** | A running instance of a program, given its own private slice of memory by the operating system. Typing `java Hello` starts one. |
| **`java` launcher** | The small native program (in the JDK's `bin` folder) that you actually run when you type `java`. It starts up a JVM instance inside the current process. |
| **Metaspace** | HotSpot's real, modern implementation of the "Method Area" concept — where class metadata and static fields actually live in memory (Java 8+). Older tutorials may call this "PermGen". |
| **Module (`java.base`)** | Since Java 9, the standard library is organized into modules rather than one big `rt.jar` file; `java.base` is the core module containing classes like `String` and `Object`. |
| **Bytecode** | A compact, CPU-neutral set of instructions produced by the compiler and stored in a `.class` file. No real processor runs it directly — only a JVM does. |
| **JVM (Java Virtual Machine)** | A program built for a specific OS/CPU that reads bytecode and executes it, giving Java its "write once, run anywhere" behavior. |
| **`javac`** | The Java compiler — translates `.java` source files into `.class` bytecode files. |
| **Class file (`.class`)** | The binary file produced by `javac`, holding bytecode plus metadata about the class (fields, methods, constant pool, etc.). |
| **Constant pool** | A table inside a class file listing every literal (strings, numbers) and every class/field/method name/reference the class uses. |
| **Class Loader** | A JVM component that finds a class's bytecode and loads it into memory. Java uses a hierarchy of loaders (Bootstrap, Platform, Application). |
| **Delegation model** | The rule that each class loader asks its parent to try loading a class first, and only loads it itself if the parent can't. |
| **Linking** | The phase after loading, made up of Verify, Prepare, and Resolve, that gets a class ready to be initialized. |
| **Bytecode Verifier** | The JVM component that checks loaded bytecode for type safety and correctness before it's allowed to execute. |
| **Initialization** | The step where static fields are assigned their real values and static initializer blocks run. |
| **Method Area** | A memory region (shared across the whole JVM) holding class metadata, static fields, and method bytecode. |
| **Heap** | A memory region (shared across the whole JVM) where all objects and arrays are allocated — e.g. anything created with `new`. |
| **JVM Stack** | A per-thread memory region made of stack frames, one per active method call, holding local variables and partial results. |
| **Stack frame** | A single "slot" on the JVM Stack created when a method is called and destroyed when it returns, holding that method's local variables. |
| **PC (Program Counter) Register** | A small per-thread register that tracks which bytecode instruction is currently executing. |
| **Native Method Stack** | A per-thread memory region used when a thread calls native (non-Java) code, e.g. via JNI. |
| **Execution Engine** | The part of the JVM that actually runs bytecode, made up of the Interpreter and the JIT Compiler. |
| **Interpreter** | Executes bytecode one instruction at a time. Starts immediately but is slower for code that runs repeatedly. |
| **JIT (Just-In-Time) Compiler** | Compiles frequently-run ("hot") bytecode directly into native machine code so future calls run at full CPU speed. |
| **HotSpot** | The reference JVM implementation shipped with OpenJDK, named for its ability to detect and optimize "hot" code paths. |
| **Garbage Collector (GC)** | The JVM component that automatically reclaims Heap memory used by objects that are no longer reachable by the running program. |
| **GC root** | A starting point (like a local variable on an active stack, or a static field) that the Garbage Collector uses to decide which objects are still reachable. |
| **JNI (Java Native Interface)** | The mechanism that lets Java code call into native (C/C++) code, and vice versa — used, for example, deep inside console output. |

---

## §1 The Big Picture — Write Once, Run Anywhere

Java code is never executed directly by your operating system the way a C program's machine code is.
Instead, Java code goes through two translation stages: a human writes **source code**, a compiler turns it
into a CPU-neutral **bytecode**, and then a **Java Virtual Machine (JVM)** — one built specifically for your
operating system and CPU — reads that bytecode and runs it. Because every OS has its own JVM but the bytecode
itself never changes, the same `.class` file runs unmodified on Windows, Linux, or macOS. This is Java's
famous "Write Once, Run Anywhere" (WORA) promise.

```
Calculator.java  --javac-->  Calculator.class  ----+--> JVM (Windows) --> Native Windows CPU instructions
 (source code)                (bytecode,             |
  human-readable)              platform-neutral,      +--> JVM (Linux)   --> Native Linux CPU instructions
                                same file everywhere)  |
                                                        +--> JVM (macOS)   --> Native macOS CPU instructions
```
*Figure 1. The same .class file runs on any OS because each OS has its own JVM.*

> **Key idea:** the JVM — not your operating system — is what actually executes Java bytecode. Everything in
> this document (Steps 1–8) is really a closer look at the middle and right side of Figure 1.

---

## §1B JDK vs JRE vs JVM

Before going step by step, it helps to know the three letters you'll see everywhere in Java: **JDK**, **JRE**,
and **JVM**. They are often drawn as three nested boxes, one inside the other:

```
┌─ JDK — Java Development Kit ─────────────────────────────────────┐
│  Everything needed to WRITE and BUILD Java programs              │
│  (+ javac compiler, debugger, other dev tools)                   │
│                                                                    │
│  ┌─ JRE — Java Runtime Environment ─────────────────────────┐    │
│  │  Everything needed to RUN a Java program (not compile)   │    │
│  │                                                            │    │
│  │  ┌─ JVM ──────────┐   ┌─ Java Libraries ───────────────┐ │    │
│  │  │ runs bytecode, │   │ java.lang, java.util, java.io   │ │    │
│  │  │ manages memory │   │ (String, ArrayList, Math, ...)  │ │    │
│  │  └────────────────┘   └──────────────────────────────────┘ │    │
│  └────────────────────────────────────────────────────────────┘    │
└────────────────────────────────────────────────────────────────────┘
```
*Figure 1B. JDK contains the JRE, and the JRE contains the JVM plus the standard libraries.*

> **A note on this diagram:** this three-box picture describes Java **8 and earlier**, when Oracle shipped
> the JRE as a separate, smaller download for people who only wanted to *run* Java programs. Since **Java 11**,
> Oracle stopped distributing a standalone JRE — installing the JDK is now the only option, and it includes
> everything the JRE used to provide. So today "JRE" is more of a *concept* (the run-time pieces of the JDK)
> than a separate thing you install. Beginners can still think in terms of these three boxes — just know
> you'll only ever download "the JDK" in practice.

### What each one is for

| Layer | Purpose | Contains |
|---|---|---|
| **JDK** (Java Development Kit) | For **developing** Java programs | The compiler (`javac`), debugging tools, documentation tools, **and** a full JRE |
| **JRE** (Java Runtime Environment) | For **running** Java programs | The JVM, plus the standard Java libraries (`java.lang`, `java.util`, etc.) |
| **JVM** (Java Virtual Machine) | Actually **executes** bytecode | Class loader, runtime memory areas, execution engine, garbage collector — everything covered in Steps 3–7 |

### Why a program needs more than just the JVM

The JVM by itself only knows how to run bytecode — it doesn't know what an `ArrayList` or a `String` is.
Almost every real Java program uses classes like:

```java
System.out.println();
ArrayList
String
Math
```

These come from the **standard Java libraries**, which live alongside the JVM inside the JRE. So a JVM
running completely alone — without those libraries — could not run a normal Java program. This is why the
JRE is described as "JVM + Libraries":

```
JRE
├── JVM
└── Java Libraries (java.lang, java.util, java.io, ...)
```

> **One-line summary:** the JDK is what you install to *build* Java software; the JRE (bundled inside the JDK
> today) is what actually *runs* it; and the JVM, sitting inside the JRE, is the piece that reads bytecode and
> executes it instruction by instruction.

---

## §2 The Running Example

To keep every step concrete, this whole document traces **one single program** — a small `Calculator` class.
It is intentionally simple but rich enough to show a static field, an object, a method call, and a local
variable, which is exactly the combination we need to explain class loading, memory areas, and execution
later on.

```java
public class Calculator {

    // static field: belongs to the class itself, not to any one object
    static int multiplier = 2;

    public static void main(String[] args) {
        Calculator calc = new Calculator();   // object created on the Heap
        int result = calc.multiply(5);        // method call -> new stack frame
        System.out.println("Result: " + result);
    }

    public int multiply(int num) {
        int output = num * multiplier;        // local variable -> lives on the Stack
        return output;
    }
}
```

We will refer back to this exact file — `Calculator.java` — in every step below. Wherever you see a
**> Our example:** callout, it shows what is happening specifically to *this* program at that step.

---

## Step 1 — Writing the Source Code

Everything starts with a plain text file saved with a `.java` extension — in our case, `Calculator.java`. At
this stage the file is just text; the operating system does not know anything about "classes" or "methods".
The filename must match the name of the `public` class inside it, which is why our file is named
`Calculator.java` and not something else — the compiler enforces this in the next step.

**What happens here**
- The developer writes code following Java's syntax rules (classes, methods, statements).
- The file is saved with UTF-8 (or platform default) text encoding and a `.java` extension.
- No translation has happened yet — this file cannot be executed by anything yet.

> **Our example:** `Calculator.java` is saved to disk, containing the class shown in §2, with one static
> field (`multiplier`) and two methods (`main` and `multiply`).

---

## Step 2 — Compilation — Source Code → Bytecode

The Java compiler, `javac`, reads the `.java` file and translates it into **bytecode** — a compact,
platform-independent instruction set that no real CPU understands directly, but that every JVM does. The
compiler also performs full syntax and (mostly) type checking at this stage; if the code doesn't compile, no
`.class` file is produced at all.

```
$ javac Calculator.java
# produces: Calculator.class
```

**What happens here**
1. **Lexical analysis & parsing** — javac breaks the source into tokens and builds an Abstract Syntax Tree (AST).
2. **Semantic analysis** — type checking, resolving variable/method references, checking access modifiers.
3. **Bytecode generation** — the compiler emits one `.class` file per class/interface, containing bytecode
   instructions (each called an *opcode*, e.g. `iload`, `invokevirtual`, `imul`) plus a symbol table called
   the **constant pool**.

### Inside Calculator.class (simplified view)

A `.class` file is a binary file, but conceptually it holds these sections:

| Section | Contains |
|---|---|
| Magic number | `0xCAFEBABE` — identifies the file as a valid class file |
| Version | Major/minor class file version (tied to the JDK used to compile it) |
| Constant pool | All string literals, class/field/method names and references used, e.g. `"Result: "`, `Calculator`, `multiply` |
| Access flags | `public`, `final`, etc. |
| Fields | Description of `multiplier` (type `int`, static) |
| Methods | Bytecode instructions for `main` and `multiply` |
| Attributes | Debug info, line numbers, etc. (if compiled with `-g`) |

The bytecode for our `multiply` method looks roughly like this (simplified, not exact javap output):

```
// int multiply(int num) { return num * multiplier; }
0: iload_1        // push local variable 'num' onto the operand stack
1: getstatic  #7  // push the static field 'multiplier' onto the stack
4: imul           // pop both, multiply, push the result
5: istore_2       // pop result into local variable 'output'
6: iload_2        // push 'output' back onto the stack
7: ireturn        // return the int on top of the stack to the caller
```

> **Our example:** running `javac Calculator.java` produces exactly one file, `Calculator.class`, containing
> the bytecode for `main` and `multiply`, plus a constant-pool entry recording that `multiplier` is a static
> `int` field.

> **Note:** compilation is platform-independent — the same `Calculator.class` is produced whether you compile
> on Windows, Linux, or macOS, and that exact file is what gets copied to any machine that wants to run it.

---

## Step 2B — From Terminal to Running Program

We now have `Calculator.class` sitting on disk. This section zooms into exactly what happens the moment you
type a run command in the terminal — **before** the JVM gets to the Class Loading, Verification, and
Execution steps described next. (For this section we'll use the classic example `java Hello`, since that's
the command most people type first — the same sequence applies to `java Calculator`.)

```
$ java Hello
```

### Step A — The OS starts a new process
The operating system treats `java` as an ordinary program. It starts a new **process** for it — a running
instance of a program, with its own private slice of memory — exactly the way it would for any other
executable you launch.

### Step B — The `java` launcher starts a JVM instance
`java` is a small native (non-Java) executable that ships inside the JDK/JRE's `bin` folder. It does not
contain the JVM's logic itself — instead, it loads the JVM as a shared library (`libjvm.so` on Linux,
`jvm.dll` on Windows) and asks it to start up, using something called the **JNI Invocation API**. The result
is a running JVM instance living inside the process the OS just created.

> **Note:** you'll sometimes see this described loosely as "the JRE starts the JVM." That's true in spirit —
> the launcher and the JVM library both come from the JRE's files on disk — but more precisely, it's the
> `java` **launcher program** that actively creates and starts the JVM instance. The JRE itself is just a
> folder of files; it doesn't "do" anything on its own.

### Step C — The JVM requests memory from the OS
Once running, the JVM asks the operating system for a block of memory (RAM). The OS grants it, and from that
point on, **managing that memory is entirely the JVM's job** — not the OS's, and not the JRE's. The JVM
divides its memory into the Runtime Data Areas covered in full in Step 5: the Heap, the Stack(s), the Method
Area, and a few smaller regions.

### Step D — The JVM loads the required classes
Using the Class Loader Subsystem (covered in full in Step 3, next), the JVM loads:
- Your own compiled class — e.g. `Hello.class` or `Calculator.class`
- Core classes it needs behind the scenes, such as `java.lang.Object`, `java.lang.String`, and `java.lang.System`

These core classes are part of the standard Java libraries described in §1B — physically, the JVM loads them
and the JRE is where they conceptually "live". So: the JVM does the loading, the JRE supplies the files being
loaded.

### Step E — Bytecode execution begins
With classes loaded, the JVM verifies the bytecode, then either interprets it or JIT-compiles it to native
machine code, and starts executing. This is exactly where Steps 4–6 below pick up.

> **The short version:** OS starts a process → the `java` launcher starts a JVM instance inside it → the JVM
> asks the OS for memory and manages it internally → the JVM loads your class plus any core classes it needs
> → the JVM verifies and runs the bytecode. Steps 3 through 6 below zoom into the last two parts of that list
> in full detail.

---

## Step 3 — Class Loading

When you run `java Calculator`, the JVM starts up and its **Class Loader Subsystem** is responsible for
finding `Calculator.class` on disk, reading its bytes, and constructing an in-memory representation of the
class (a `java.lang.Class` object) that the rest of the JVM can use. Java uses a hierarchy of class loaders
that follows a **delegation model**: each loader asks its parent to try loading the class first, and only
loads it itself if the parent can't find it.

```
              Bootstrap ClassLoader
       (loads core JDK classes, java.lang.* —
              written in native code)
                       │
                 delegates up
                       │
              Platform (Extension) ClassLoader
      (loads JDK platform modules, e.g. java.sql, java.xml)
                       │
                 delegates up
                       │
              Application (System) ClassLoader
        (loads classes from your classpath —
              e.g. Calculator.class)
                       │
         not found upstream -> loads it itself
                       ▼
              Calculator.class loaded
        as a java.lang.Class object in memory
```
*Figure 2. Delegation model: each loader asks its parent first; the Application ClassLoader ultimately loads
our own `Calculator.class` since it's on the classpath, not in the JDK itself.*

**What happens here — three sub-phases**

| Phase | What it does |
|---|---|
| **Loading** | Finds `Calculator.class` bytes (from classpath, JAR, etc.) and creates a `Class` object representing it in the Method Area. |
| **Linking** | Split into Verify (Step 4) → Prepare (allocate memory for static fields and set default values, e.g. `multiplier = 0` temporarily) → Resolve (turn symbolic references in the constant pool into direct references). |
| **Initialization** | Runs static initializers and static field assignments in source order — this is when `multiplier` actually becomes `2`. |

> **Our example:** the Application ClassLoader loads `Calculator.class` because it sits in our own classpath,
> not inside the JDK. During *Prepare*, `multiplier` is temporarily set to the default value `0`; during
> *Initialization*, the assignment `static int multiplier = 2;` actually runs, so by the time `main` executes,
> `multiplier` holds `2`.

> **Note:** core classes like `java.lang.String` used to be stored in a single file called `rt.jar` (Java 8
> and earlier). Since Java 9's module system, they live inside a modular runtime image instead, accessed via
> a special `jrt:/` path rather than a plain jar file. The concept stays the same either way — the JVM still
> needs to find and load these classes — only the storage format changed.

---

## Step 4 — Bytecode Verification

Before any bytecode is allowed to run, the JVM's **Bytecode Verifier** checks it for safety. This is one of
Java's key security guarantees: even bytecode from an untrusted source (e.g. downloaded over a network)
cannot corrupt the JVM's memory or bypass access rules, because it is checked structurally before execution.

**What the verifier checks**
- Every instruction has valid operand types (e.g. you can't `imul` a `String` and an `int`).
- The operand stack never underflows or overflows.
- Branch instructions only jump to valid instruction boundaries.
- Access modifiers (`private`, `protected`, etc.) are respected.
- Local variables are not read before being assigned a value.
- No object is used as a type it wasn't declared as (illegal type casts are caught here or at cast time).

> **Our example:** the verifier confirms that `multiply`'s bytecode (`iload_1`, `getstatic`, `imul`,
> `istore_2`, `iload_2`, `ireturn` — from Step 2) only ever pushes/pops `int` values and that the method
> really does return an `int` as its signature promises. If this file had been hand-edited to push a `String`
> where an `int` was expected, the verifier would reject it with a `VerifyError` before a single instruction
> ran.

> **Note:** if verification fails, the JVM throws `java.lang.VerifyError` and the class is never executed —
> this happens before Step 5, so a corrupted class file can never touch the runtime memory areas below.

---

## Step 5 — Runtime Data Areas

Once a class is loaded, verified, and initialized, the JVM needs places in memory to actually run it. These
are the **Runtime Data Areas**. Some are shared by the whole JVM process; others exist *per thread* — since
`main` runs on its own thread, our example uses one of each per-thread area.

```
┌─ JVM Process Memory ─────────────────────────────────────────────────┐
│                                                                        │
│ ┌─ Method Area (shared, 1/JVM) ──┐  ┌─ Heap (shared, 1/JVM) ────────┐ │
│ │ • Calculator class metadata     │  │ • the "calc" object           │ │
│ │ • static field: multiplier = 2  │  │   (created by new Calculator())│ │
│ │ • constant pool                 │  │ • all objects & arrays live   │ │
│ │ • bytecode for main, multiply   │  │   here; managed by GC (Step7) │ │
│ └──────────────────────────────────┘  └────────────────────────────────┘ │
│                                                                        │
│ Per-thread areas (one set for the "main" thread):                    │
│ ┌─ JVM Stack ──────────────┐ ┌─ PC Register ──┐ ┌─ Native Method Stack ┐│
│ │ frame: multiply(5)       │ │ points to the   │ │ used for native      ││
│ │   num = 5, output = 10   │ │ current bytecode│ │ (JNI/C) calls, e.g.  ││
│ │-------------------------- │ │ instruction     │ │ System.out's native  ││
│ │ frame: main()             │ └─────────────────┘ │ I/O                  ││
│ │   args, calc -> (Heap ref)│                      └──────────────────────┘│
│ │   result = 10             │                                              │
│ │   (grows downward)        │                                              │
│ └───────────────────────────┘                                              │
└────────────────────────────────────────────────────────────────────────────┘
```
*Figure 3. Runtime data areas for our example while `multiply(5)` is executing.*

**What happens here**

| Area | Scope | Holds, in our example |
|---|---|---|
| Method Area | Shared (1 per JVM) | `Calculator` class metadata, the static field `multiplier`, constant pool, method bytecode |
| Heap | Shared (1 per JVM) | the `calc` object created by `new Calculator()` |
| JVM Stack | Per thread | a stack frame for `main()`, and — while it's running — a nested frame for `multiply(5)` |
| PC Register | Per thread | tracks which bytecode instruction (from Step 2's listing) is currently executing |
| Native Method Stack | Per thread | used when a native method is called, e.g. deep inside `System.out.println` |

> **Note on names:** "Method Area" is the name used in the official JVM Specification — it's an abstract
> concept, not a specific implementation. In real HotSpot JVMs (Java 8 onward), this area is actually
> implemented as **Metaspace**, which lives in your computer's regular memory rather than the Heap. (Before
> Java 8, it was called "PermGen" and worked a bit differently — you may still see that older term in some
> tutorials.)

> **Our example, traced step by step:**
> 1. `main()` starts → one stack frame is pushed for it, holding `args`.
> 2. `new Calculator()` → a new object is allocated on the Heap; a reference to it is stored in the local
     >    variable `calc` inside `main`'s frame.
> 3. `calc.multiply(5)` is called → a **new** stack frame is pushed on top, containing the parameter `num = 5`.
> 4. Inside that frame, `output = num * multiplier` reads `num` from the current frame and `multiplier` from
     >    the Method Area, computing `10`.
> 5. `return output;` → the value `10` is handed back to the caller and `multiply`'s stack frame is **popped**
     >    (destroyed).
> 6. Back in `main`'s frame, `result` is set to `10`.

---

## Step 6 — The Execution Engine — Interpreter + JIT Compiler

The **Execution Engine** is the part of the JVM that actually reads bytecode instructions and carries them
out. Modern JVMs (like HotSpot, the reference JVM shipped with OpenJDK) use two complementary techniques so
that programs start quickly *and* run fast once they've been going for a while.

```
Bytecode                Interpreter               HotSpot Profiler
(e.g. multiply()'s   -->  executes bytecode  --> flags multiply() as
 iload/imul/ireturn)      one instr at a time,     a "hot method" if
                          starts instantly          called many times
                                                          │
                                                          ▼
                                                  JIT Compiler (C1 / C2)
                                             compiles that method directly
                                             to native machine code       ──> Native code
                                                                               (cached & reused,
                                                                                no more interpreting)
```
*Figure 4. HotSpot's mixed-mode execution: interpret first, compile the hot paths later.*

**What happens here**
- **Interpreter** — reads bytecode and executes it directly, instruction by instruction. Simple and starts
  immediately, but slower for code that runs repeatedly.
- **Profiler** — the JVM counts how often each method executes and watches for "hot" methods/loops.
- **JIT (Just-In-Time) Compiler** — once a method is "hot", the JIT compiles it straight to native machine
  code for the actual CPU, so future calls skip interpretation entirely. HotSpot has two JIT tiers: **C1**
  (client compiler — compiles quickly, less optimized) and **C2** (server compiler — slower to compile,
  heavily optimized), typically used together in "tiered compilation".

> **Our example:** called once, `multiply(5)` simply gets interpreted — the interpreter executes the six
> instructions from Step 2 and produces `10`. If this same program instead called `multiply` inside a loop
> millions of times, HotSpot's profiler would eventually flag it as hot, and the JIT would replace the
> interpreted version with compiled native code, transparently, while the program keeps running.

---

## Step 7 — Garbage Collection

Java manages Heap memory automatically. The **Garbage Collector (GC)** periodically looks for objects on the
Heap that are no longer reachable from any active thread, stack, or static field, and reclaims their memory
so the program doesn't have to manage `malloc`/`free` by hand.

**What happens here**
- The GC treats certain locations as **GC roots** — active stack frames' local variables, static fields, etc.
- Starting from the roots, it traces every reference reachable from them (a "reachability" or "mark" phase).
- Any Heap object that *cannot* be reached from any root is garbage, and its memory is reclaimed
  ("sweep"/"compact").
- Modern collectors (G1, ZGC, Shenandoah) do most of this concurrently with the running program to minimize
  pauses.

> **Our example:** while `main()` is running, the local variable `calc` (a GC root, since it lives in an
> active stack frame) keeps the `Calculator` object on the Heap alive — it is reachable, so the GC leaves it
> alone. Once `main()` returns and its stack frame is popped, `calc` no longer exists anywhere, the object
> becomes unreachable, and on the next GC cycle its memory is reclaimed.

> **Note:** in this tiny program the JVM exits right after `main()` returns, so in practice the whole process
> (and its memory) is torn down by the OS before the GC would even need to run — but the reachability logic
> above is exactly what would happen in a longer-lived program.

---

## Step 8 — Program Output

The last line of `main`, `System.out.println("Result: " + result);`, calls into `java.io.PrintStream`, which
ultimately calls a **native method** (implemented in C, via JNI — the Java Native Interface) to actually write
bytes to the operating system's standard output stream. This is the one place our simple example leaves the
"pure JVM" world and briefly touches native code, using the Native Method Stack from Figure 3.

```
$ java Calculator
Result: 10
```

> **Our example:** `result` (10) is concatenated with the string `"Result: "`, producing `"Result: 10"`,
> which is written to the console — the visible, final effect of everything described in Steps 1–7.

---

## End-to-End: All Steps Together

```
Step 1 — Write Calculator.java
        │
        ▼
Step 2 — javac compiles it to Calculator.class (bytecode)
        │
        ▼
Step 3 — Class Loader Subsystem loads & initializes Calculator (multiplier=2)
        │
        ▼
Step 4 — Bytecode Verifier checks type & stack safety
        │
        ▼
Step 5 — Runtime Data Areas set up
         (Method Area: class+static · Heap: calc object · Stack/PC per thread)
        │
        ├──────────────────────────┬───────────────────────────────┐
        ▼                          ▼                               │
Step 6 — Execution Engine   Step 7 — Garbage Collector              │
 (Interpreter runs bytecode; (reclaims calc's memory once           │
  JIT compiles hot methods)  main() returns)                        │
        │                          │                                │
        └──────────────┬───────────┘                                │
                        ▼                                            │
Step 8 — Output: "Result: 10" printed via native I/O (JNI)  ◄─────────┘
                        │
                        ▼
             Program complete — JVM exits
```
*Figure 5. The complete lifecycle of `Calculator.java`, from text file to console output.*

### The pipeline, step by step

- **Step 1 — Write the source code:** `Calculator.java` is written as plain text, following Java syntax.
- **Step 2 — Compile:** `javac` translates the source into platform-independent bytecode, producing
  `Calculator.class`.
- **Step 3 — Class loading:** the Class Loader Subsystem (Bootstrap → Platform → Application) locates
  `Calculator.class`, loads it, and initializes the static field `multiplier` to `2`.
- **Step 4 — Bytecode verification:** the Verifier checks the loaded bytecode for type safety and stack
  correctness before anything is allowed to run.
- **Step 5 — Runtime data areas set up:** the Method Area holds class metadata and `multiplier`; the Heap
  holds the `calc` object; the Stack and PC Register track execution per thread.
- **Step 6 — Execution engine runs it:** the Interpreter executes the bytecode instruction by instruction; if
  a method runs often enough, the JIT compiler converts it to native code.
- **Step 7 — Garbage collection:** once `main()` returns and `calc` is no longer reachable, its Heap memory
  becomes eligible for reclamation.
- **Step 8 — Output:** `System.out.println` hands off to native I/O (via JNI) and `"Result: 10"` is printed
  to the console.

---

## Test Your Knowledge

Try answering each question yourself before reading the answer below it. If you can answer all of these
confidently, you've understood the core ideas in this document.

**Q1. Who gives out memory — the JRE or the JVM?**
> **Answer:** The **JVM**. It requests a block of memory from the operating system when it starts, and then
> manages and subdivides that memory itself (into the Heap, Stack, Method Area, etc.). The JRE is just a
> collection of files on disk — it doesn't perform actions like this.

**Q2. What does `javac` actually produce?**
> **Answer:** A `.class` file containing **bytecode** — a platform-independent set of instructions. It is not
> native machine code, so no CPU can run it directly; only a JVM can.

**Q3. True or False: since Java 11, you can still download a standalone JRE from Oracle.**
> **Answer: False.** Oracle stopped offering a separate JRE download starting with Java 11 — the JDK is now
> the only download, and it includes everything the JRE used to provide.

**Q4. What are the three sub-phases of Class Loading, in order?**
> **Answer: Loading** (find the bytecode and create a `Class` object) → **Linking**, itself made of Verify,
> Prepare, and Resolve → **Initialization** (run static initializers and assign real values to static fields).

**Q5. What's the difference between the Interpreter and the JIT compiler?**
> **Answer:** The **Interpreter** executes bytecode one instruction at a time — it starts instantly but is
> slower for code that runs repeatedly. The **JIT compiler** compiles frequently-run ("hot") methods directly
> into native machine code, so later calls skip interpretation and run at full CPU speed.

**Q6. Which memory area holds a class's static fields and method bytecode — the Heap or the Method Area?**
> **Answer:** The **Method Area** (implemented as Metaspace in real JVMs). The **Heap** is for objects created
> with `new`, like our `calc` object — not for static fields or class metadata.

**Q7. Does the JDK contain the JRE, or does the JRE contain the JDK?**
> **Answer:** The **JDK contains the JRE** (which in turn contains the JVM). The JDK adds development tools
> like `javac` on top of everything the JRE provides.

**Q8. What actually starts the JVM when you type `java Hello` — the operating system, or the `java` launcher?**
> **Answer:** The **`java` launcher**. The OS only starts a process for the `java` program; it's the launcher
> itself that loads the JVM as a library and starts an instance of it, using the JNI Invocation API.

**Q9. Why can't the JVM run a normal Java program all by itself, with no libraries?**
> **Answer:** Because almost every real program uses classes the JVM doesn't build in, like `String`,
> `ArrayList`, or `Math`. These come from the standard Java libraries bundled in the JRE, not from the JVM
> itself.

**Q10. In our `Calculator` example, when does the `calc` object on the Heap become eligible for garbage
collection?**
> **Answer:** Once `main()` returns and its stack frame is popped. At that point, `calc` is no longer
> reachable from any GC root, so the Garbage Collector is free to reclaim its memory.