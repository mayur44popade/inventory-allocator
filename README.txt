Application is built using Gradle, Java 11, JUnit and IntelliJ.
You can import the project using build.gradle file. All required dependencies are added.

Implementation:

For this project, I tried to use Test Driven Development as much as I can. You can see my test class at different stages
during development using git commits.

All test cases(alon with the ones provided by you) that I have written are in src->test->java->InventoryAllocatorTest.class
Once you build the project, you can run all test cases.
At the time of submission, all 11 test cases are passing.

I started developing application using Java but midway I observed that it is very easy to implement using Javascript(nodejs).
Since I was already close to finish I completed it in Java.

Issues/Boundary cases:
1. There was no mention of what to do if ordered quantity is greater than what's available in inventory.
2. I tried to use DeepEquals method of Assert but did not succeed.
3. When Java converts a HashMap into a String, it adds '=' character after key. Expected character was ':'. So I changed expected
    outputs to include '=' instead of ':'. I used string comparison for testing. But there are other ways to do testing.


