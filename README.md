# 🔐 Password Strength Analyzer

A dual-mode Java application that evaluates password security using entropy calculation, character validation, repetition detection, and dictionary comparison.

This project supports both **Command-Line Interface (CLI)** and **Graphical User Interface (GUI)** modes.

Built using **Java 21** and **Maven**, packaged as an executable JAR.

---

## 🚀 Features

- ✅ Entropy-based strength calculation
- ✅ Minimum security condition checks:
  - At least one letter
  - At least one digit
  - At least one special character
- ✅ Password length validation (12–15 characters recommended)
- ✅ Detection of repeated characters
- ✅ Dictionary comparison against common passwords
- ✅ Dual interface support (CLI + GUI)
- ✅ Executable JAR packaging

---

## 🧠 How It Works

The analyzer evaluates password strength using:

1. **Length-based entropy formula**
2. **Character diversity analysis**
3. **Dictionary matching against common passwords**
4. **Repetition penalty detection**

If the password passes all validation checks, it calculates entropy in bits and categorizes the strength level.

---

## 🛠️ Tech Stack

- Java 21
- Maven
- OOP Principles (Inheritance, Encapsulation)
- HashSet for dictionary storage
- CLI using Scanner
- GUI (Java-based interface)

---

## 📦 Instructions

1. Download PasswordChecker-1.0.0.jar
2. Open Terminal and run command:
java -jar PasswordChecker-1.0.0.jar
