package hw;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import sun.misc.Signal;

import java.util.*;

// see https://stackoverflow.com/questions/1963806/#21699069
// why we're using this implementation instead of java.util.ArrayQueue!

public class MainTestable {

  public static int LAST_N_WORDS = 10;

  public static void main(final String[] args) {

    // TODO consider using a command-line option library

    // perform argument validity checking
    if (args.length > 1) {
      System.err.println("usage: ./target/universal/stage/bin/consoleapp [ last_n_words ]");
      System.exit(2);
    }

    int lastNWords = LAST_N_WORDS;
    try {
      if (args.length == 1) {
        lastNWords = Integer.parseInt(args[0]);
        if (lastNWords < 1) {
          throw new NumberFormatException();
        }
      }
    } catch (final NumberFormatException ex) {
      System.err.println("argument should be a natural number");
      System.exit(4);
    }

    // properly terminate on SIGPIPE received from downstream
    // see also http://lucproglangcourse.github.io/imperative.html#the-role-of-console-applications
    if (System.getProperty("os.name").indexOf("Windows") < 0) {
      Signal.handle(new Signal("PIPE"), (final Signal sig) -> System.exit(1));
    }

    final Iterator<String> input = new Scanner(System.in).useDelimiter("(?U)[^\\p{Alpha}0-9']+");

    // an observer instance that sends updates to the console
    final Output outputToConsole = (final Queue<String> value) -> System.out.println(value);

    final WindowMaker wm = new WindowMaker(lastNWords);
    wm.slidingWindow(input, outputToConsole);
  }
}
