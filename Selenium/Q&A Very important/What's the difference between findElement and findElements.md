**8. What's the difference between `findElement` and `findElements`? How do you avoid `NoSuchElementException`?**
`findElement` throws `NoSuchElementException` if no match is found; `findElements` returns an empty list instead. 
	To safely check existence, use `findElements(...).size() > 0` or wrap in an explicit wait condition rather than try-catch for every check
	(try-catch is expensive and hides real failures if overused).
