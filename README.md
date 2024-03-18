# Implementing TDD, Mutation, Mocking, Property-based Testing, and Continuous Integration
This project implements a simple MyString class with two methods:
1. indexOfString(String s1, String s2, int pos) - Finds the index at which s2 occurs in s1 starting at position pos in s1.
2. replace(String s, String s1, String s2) - Replaces all occurences of s1 in s with s2.

Both of these methods were implemented using the TDD approach, and efforts were made to highlight that by intentionally pushing RED and GREEN commits to the repository for them to be run by GitHub Actions.

Mutation testing was then performed using [PITtest](https://pitest.org/), mocking was performed using [Mockito](https://site.mockito.org/), and property-based testing was implemented using [jqwik](https://jqwik.net/).
## Instructions
### Executing tests
Simply executing the package Maven goal will run the tests:
```bash
mvn clean package
```
Alternatively, you can run either 
```bash
mvn test
```
or 
```bash
mvn verify
```
### Mutation test reports
To execute mutation tests and obtain their reports, the following command should be ran:
```bash
mvn test-compile org.pitest:pitest-maven:mutationCoverage
```
