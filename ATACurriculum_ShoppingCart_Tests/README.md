# Shopping Cart

Here's your chance to make a test plan and then turn it into code, so that you can repeat your test plan every
edit-build-test cycle without repetitive manual testing!

We'll be testing a class, `ShoppingCart`, that represents a customer's items that they would like to
order. It's been partially implemented, and needs some tests to verify that the existing methods are working as
expected. They've passed lots of manual testing, but we want to be more efficient by automating them.

## Getting Started

We hope these steps are becoming more familiar:

1. Create a new workspace named `ATA_ShoppingCart` , using the version set `ATACurriculum_ShoppingCart/<cohort>`,
replacing `<cohort>` with the name of your cohort (i.e. C2020July)
    1. `cd` to the directory where you put all of your workspaces
    1. `brazil ws --create --name ATA_ShoppingCart --versionset ATACurriculum_ShoppingCart/<cohort>`
1. Change directory into the workspace
    1. `cd ATA_ShoppingCart`
1. Use this package, `ATACurriculum_ShoppingCart_Tests`, in your new workspace
    1. `brazil ws --use --package ATACurriculum_ShoppingCart_Tests`
1. Change directory into the package you just created
    1. `cd src/ATACurriculum_ShoppingCart_Tests`
1. Build once to avoid IntelliJ import errors
    1. `rde wflow run -v`
1. Open the workspace as a new project in IntelliJ:
    1. File -> New -> Project from Existing Sources...
    1. Uncheck all when it says to import source files that it has found
    1. After opening, Brazil -> Sync from Workspace (to load the package into IntelliJ)
    1. Find the `ShoppingCartTest` Java Class under `src/test/java/...` and open it
    1. Look for the "Project SDK is not defined" banner at the top of the IntelliJ window and click "Setup SDK"
        1. Select the suggested Java SDK (should be 1.8 or higher)
        1. You may need to go to File -> Project Structure..., select the "Project" link on the left, and set the
           Project language level to "SDK Default" (which is probably Level 8).
1. Find the `public static void main()` method near the bottom of `ShoppingCartTest`. Click the green arrow in the
   margin to the left of the window and select "Run ShoppingCartTest.main()" and make sure that the console prints out
   all "PASS" results (in the bottom of the IntelliJ window).

## Get to Know the Class Under Test

"System under test" is how we refer to the code that we're testing while we're developing. In this case, we're
testing a single class, so we can call it the "class under test". Given the "Shopping Cart" section above, what do
we think the "class under test" is here? Right! `ShoppingCart`. But wait, where is it? To make sure we're thinking
about how to test the class, we're actually not including it in this package (usually the class and its tests are in
the same package). But at least we can read the javadoc to see what the intended behaviors of all of its
methods are.

1. Download the [ShoppingCart javadoc](https://amazon.awsapps.com/workdocs/index.html#/document/e8c070e0d7d9f69efee105be104f7232683ff942cb911113c15187aae72fb2b2),
   double-click to expand contents, open index.html, and read through the documentation for `ShoppingCart`'s methods.

## Write Your Test Cases (Think - Pair - Share)

### Think

1. Decide as a group who will work on each of the two methods
1. Take a few minutes and write down some test cases in `test_cases/shopping_cart_test_cases.md`.
1. Write at least 2 test cases, including the "happy path" test case.
    * Use the test case template provided in the file.

### Pair

1. In your groups, compare your lists by sharing screens or reading them aloud.
1. Based on your group's questions and feedback, improve your test cases to make them clearer.
1. If your group agrees that a test makes sense, feel free to add any you didn't have to your list.
1. PRESENTER: Be prepared to share some of the test cases your group has come up with when the class reconvenes.
1. ANSWER: the first question on the Canvas quiz
   ("Paste in your test cases you entered into shopping_cart_test_cases.md")

### Share

1. PRESENTER: Be ready to read one of your test cases out to the class. You can pick the one that your group thought
   was the best example of a good test case.

## Build!

1. Choose two of your test cases you wrote above (one should be the "happy path" case), and implement them
   in the `ShoppingCartTest` class in your workspace.
    1. Note: You will *NOT* be pushing these changes to the remote repository.
1. Each test case will be implemented as a separate method that you call from `runAllTests()`.
1. We've implemented two tests for you as examples (one for each `ShoppingCart` method under test).
1. Make sure that all of your tests pass. If not, take a closer look at your test to make sure it's in agreement
   with the `ShoppingCart` javadoc, and that your tests are implemented correctly.
1. We recommend running the `main()` method from within IntelliJ, as you did above in "Getting Started".
1. ANSWER: the second question on the Canvas page (Did all of your tests pass right away or did you have to make fixes?)
