# Learning objectives

* simple console app example
* experience with Git source code management
* building with SBT
* stream processing (finite vs. infinite/unbounded)
* pipes and filters architecture
* representing streams using the Iterator design pattern
* separation of processing and I/O concerns
* testability using the Observer design pattern
* time/space complexity and scalability

# System requirements

* [Java 11 SDK or later](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [SBT](https://www.scala-sbt.org/1.x/docs/Setup.html)

You may also be able to install these requirements through your package manager or [SDKMAN!](https://sdkman.io/).

Alternatively, you can use an IDE such as IntelliJ IDEA or Android Studio.

# Description

This application maintains a running FIFO queue of the last n words read from the standard input (stdin).
For each word read, the application prints the updated queue to the standard output (stdout) in the format

    [w1, w2, ..., wn]

where wn is the most recent word read, and w1 is the oldest word still in the queue.

The default queue capacity is 10, and a different capacity can be passed as a command-line argument.

If multiple words are entered on the same line, the application processes them separately, using non-word characters, such as whitespace, as separators.

# Examples

```
$ sbt "run 3"
[info] ...
Multiple main classes detected, select one to run:

 [1] hw.Main
 [2] hw.MainLeaky
 [3] hw.MainTestableva-sbt
1
[info] running hw.Main
w1 w2
[w1]
[w1, w2]
w3
[w1, w2, w3]
w4 w5
[w2, w3, w4]
[w3, w4, w5]
w6
[w4, w5, w6]
EOF
```

# Running the application

On Linux or Mac OS X:

    $ sbt run

or

    $ sbt "run arg1 arg2 arg3"

On Windows:

    > sbt run

or

    > sbt "run arg1 arg2 arg3"

SBT will then prompt you to choose the specific main class you want to run.

# Running a specific main class directly

On Linux or Mac OS X:

    $ sbt "runMain hw.Main"

or

    $ sbt "runMain hw.Main arg1 arg2 arg3"

On Windows:

    > sbt "runMain hw.Main"

or

    > sbt "runMain hw.Main arg1 arg2 arg3"

# Running the tests

On Linux or Mac OS X:

    $ sbt test

On Windows:

    > sbt test

# Running the application outside SBT

This avoids the performance overhead of running the application through SBT and allows passing command-line arguments directly:

On Linux or Mac OS X:

    $ sbt stage
    $ ./target/universal/stage/bin/main arg1 arg2 arg3

On Windows:

    > sbt stage
    > .\target\universal\stage\bin\main arg1 arg2 arg3
