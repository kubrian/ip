# Declaration of AI Usage

Usage of AI is restricted to supercomplete (enhanced autocomplete), via [Codeium](https://codeium.com/) on IntelliJ.
The following steps describes the typical usage via a representative example of test case generation.

1. Write a short comment describing the immediate (non-trivial) purpose of the next few lines I intend to write.
    1. `// Create Todo and test getDescription`
1. Start coding manually as I would, and only when the appropriate autocompletion is triggered, review and accept it.
    1. `Todo task = new Todo("name 1")`
    1. `// some assertEqual statement pops up which I review and accept.`
1. Prompt for javadoc and comments using `/**` or `//`, accept and modify to conform to established coding standards.
    1. `* Returns ... `
1. Remove the initial prompting comment if it is undesirable to be kept.
