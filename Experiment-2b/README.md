# Experiment 2b: Server-Side Form Validation in JSP

## 🎯 Aim & Objective
Enhance the employee JSP web application to implement robust server-side input validation using regular expressions and Java logic checks:
- **Employee ID:** Must not be null or blank.
- **Name:** Must contain only letters and whitespace (`^[a-zA-Z\s]+$`).
- **Age:** Must parse as an integer and fall within `18 <= age <= 65`.
- **Department:** Must match an allowed predefined option (`HR`, `Finance`, `IT`, `Sales`).
- **Email:** Must match standard email pattern (`^[\w.-]+@[\w.-]+\.[a-zA-Z]{2,}$`).
- If all validations pass, render the processed employee details table.
- If any validation fails, display clear, field-specific error messages with a link back to the form.

---

## 📦 Required Packages & Dependencies

| Package | Class | Purpose |
|---|---|---|
| `java.util.regex` | `Pattern`, `Matcher` | Regular expression matching and validation |
| `java.util` | `Calendar` | Current year calculation for birth year computation |

---

## 🛠 Step-by-Step Eclipse Setup & File Creation

### 1. In Existing or New Dynamic Web Project
- You can reuse `JSPDemo` from Experiment 2a, or create a new Dynamic Web Project (**File -> New -> Dynamic Web Project** -> `JSPValidationDemo`).

### 2. Create the JSP Files
1. Under `WebContent` (or `src/main/webapp`):
2. **Create `employeeForm.jsp`:**
   - Right-click `WebContent` -> **New -> JSP File** -> Name: `employeeForm.jsp`.
   - Copy and paste the code from [`employeeForm.jsp`](./employeeForm.jsp).
3. **Create `employeeResult.jsp`:**
   - Right-click `WebContent` -> **New -> JSP File** -> Name: `employeeResult.jsp`.
   - Copy and paste the code from [`employeeResult.jsp`](./employeeResult.jsp).
4. Save both files (`Ctrl + S` or `Cmd + S`).

---

## 🚀 How to Run & Execute in Eclipse

1. In Project Explorer, right-click `employeeForm.jsp`.
2. Select **Run As -> Run on Server** -> Select Tomcat -> Click **Finish**.
3. Open in browser:
   ```text
   http://localhost:8080/JSPDemo/employeeForm.jsp
   ```
4. **Test Scenarios:**
   - **Test 1 (Valid Input):**
     - Employee ID: `EMP200`
     - Name: `Alice Walker`
     - Age: `28`
     - Department: `IT`
     - Email: `alice@example.com`
     - Result: Formatted success table appears.
   - **Test 2 (Invalid Input):**
     - Name: `Alice 123` *(contains digits)*
     - Age: `14` *(less than 18)*
     - Email: `invalid-email` *(missing @ and domain)*
     - Result: Displays specific validation errors with a link back to the form.

---

## 🔍 Code Walkthrough

```jsp
// Validate Name
if (name == null || !name.matches("^[a-zA-Z\\s]+$")) {
    nameError = "Name must contain only letters and spaces.";
    isValid = false;
}

// Validate Age
int age = 0;
try {
    age = Integer.parseInt(ageStr);
    if (age < 18 || age > 65) {
        ageError = "Age must be between 18 and 65.";
        isValid = false;
    }
} catch (NumberFormatException e) {
    ageError = "Age must be a valid numeric value.";
    isValid = false;
}
```
- `matches(regex)` checks the entire string against the pattern.
- Exception handling around `Integer.parseInt()` ensures illegal characters in numeric inputs do not crash the server.

---

## 🧪 Expected Output

### Validation Failure Output:
```text
Validation Errors Found:
• Name: Name must contain only letters and spaces.
• Age: Age must be between 18 and 65.
• Email: Email must be a valid email address (e.g., name@example.com).

← Go back to the form
```

### Validation Success Output:
```text
Employee Information Result
+-----------------------+----------------------------------+
| Employee ID           | EMP200                           |
+-----------------------+----------------------------------+
| Name and Department   | Alice Walker (IT)                |
+-----------------------+----------------------------------+
| Birth Year            | 1998                             |
+-----------------------+----------------------------------+
| Formatted Email       | alice@example.com                |
+-----------------------+----------------------------------+
```
