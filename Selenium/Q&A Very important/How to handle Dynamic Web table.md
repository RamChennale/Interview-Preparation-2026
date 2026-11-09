#How to handle Dynamic Web table

“I never use a fixed row index, because the order changes on reload. I find the row by its key value and read the column I need. 
I wrapped this in a reusable helper, getDynamicCell(rowName, columnName), which reads the header at runtime to find the column index.”

java
By.xpath("//table[@id='taskTable']//tr[td[1][normalize-space()='Chrome']]/td[2]")


My method does that in two steps:

Find the column. It reads all the header cells (Name, CPU, Network, Memory...) and checks which one starts with ‘CPU’. That gives the column number. If no header matches, it throws a clear error instead of returning a wrong value.
Find the row and read the cell. It uses an XPath that says: ‘pick the row whose first cell says Chrome, then take the cell at the column number from step 1.’


    @Test(priority = 1)
    public void dynamicWebTableHandling1() {
        driver.get("https://testautomationpractice.blogspot.com/");
        String rowName = "Chrome";
        String colName = "CPU (%)";
        System.out.println("Chome CPU usage is : "+ getDynamicCell( rowName, colName));

    }

    public static String getDynamicCell(String rowName, String columnName) {
        List<WebElement> headers = driver.findElements(
                By.xpath("//table[@id='taskTable']//thead//th"));

        int colIndex = -1;
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().startsWith(columnName)) {
                colIndex = i + 1;            // XPath is 1-based
                break;
            }
        }
        if (colIndex == -1) {
            throw new NoSuchElementException("Column not found: " + columnName);
        }

        return driver.findElement(By.xpath(
                        "//table[@id='taskTable']//tr[td[1][normalize-space()='" + rowName + "']]/td[" + colIndex + "]"))
                .getText();
    }

}

/*

//table[@id='taskTable']//tr[td[1][normalize-space()='" + rowName + "']]/td[" + colIndex + "]

//table[@id='taskTable']//tr[td[1][normalize-space()="Chrome"]]/td[4]
* */

# 2nd approch
public void dynamicWebTableHandling2(){
        driver.get("https://testautomationpractice.blogspot.com/");
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='taskTable']//tbody/tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.get(0).getText().equals("Chrome")) {
                System.out.println("Chrome CPU: By ROW iterating  " + cells.get(1).getText());
                break;
            }
        }
    }




"This is the loop approach for a dynamic table. 
I first collect all the data rows of the table using `findElements` on `tbody/tr`. 
Then I loop through them, and for each row I get its `td` cells. 
I check whether the first cell's text is 'Chrome', and when it matches, I read the second cell, which is the CPU value, print it, and break out of the loop.

The key point is that I find the row by its content, not by its position, so it works even when the row order changes on every reload."

**If they ask about weaknesses:**
- The column index is hardcoded (`get(1)`), so it breaks if the column order changes. I'd use a header lookup to fix that.
- If 'Chrome' isn't found, the loop ends silently, so I'd add an assertion to fail the test in that case.
