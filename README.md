# DSA-FINAL-SPIRNT

## **Project Overview**
This project is a **Binary Search Tree Application** built using **Spring Boot**. It allows users to:
- Input a list of numbers to create a **balanced binary search tree**.
- View the JSON representation of the tree in a user-friendly format.
- Access previously processed trees.

The application also includes a bonus feature: the creation of a **balanced binary search tree**.

---

## **Features**
1. **Input Numbers**:
   - Users can input comma-separated numbers via the `/enter-numbers` page.
   - The numbers are processed to create a balanced binary search tree.

2. **Tree Result**:
   - The `/process-numbers` page displays the JSON representation of the binary search tree in a readable format.

3. **Previously Processed Trees**:
   - The `/previous-trees` page lists all previously processed trees, showing the input numbers and the tree structure.

4. **Bonus Feature**:
   - The application creates a **balanced binary search tree** using a recursive algorithm.

5. **Styling**:
   - A global CSS file ensures consistent styling across all pages.
   - Includes a navigation bar, styled containers, and readable JSON formatting.

---

## **Technologies Used**
- **Java 17**
- **Spring Boot 3.0**
- **Thymeleaf** (for server-side rendering)
- **H2 Database** (for storing processed trees)
- **Maven** (for dependency management)
- **JUnit 5** and **Mockito** (for testing)

---

## **Project Structure**

DSA-FINAL-SPIRNT/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── binarysearchtree/
│   │   │               ├── controller/
│   │   │               │   └── TreeController.java       # Handles HTTP requests for routes
│   │   │               ├── model/
│   │   │               │   ├── BinarySearchTree.java     # Represents the binary search tree
│   │   │               │   └── TreeNode.java             # Represents a node in the binary search tree
│   │   │               ├── repository/
│   │   │               │   └── TreeRepository.java       # Manages data storage and retrieval
│   │   │               └── service/
│   │   │                   └── TreeService.java          # Contains business logic for tree creation
│   │   ├── resources/
│   │   │   ├── static/
│   │   │   │   └── css/
│   │   │   │       └── styles.css                        # Global CSS for styling
│   │   │   └── templates/
│   │   │       ├── index.html                            # Input numbers page
│   │   │       ├── result.html                           # Tree result page
│   │   │       └── previous.html                         # Previously processed trees page
│   │   └── application.properties                        # Spring Boot configuration
├── test/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           ├── BinarySearchTreeApplicationTests.java # Main test file for the application
│   │           ├── TreeControllerTest.java               # Unit tests for TreeController
│   │           ├── TreeServiceTest.java                  # Unit tests for TreeService
│   │           └── TreeRepositoryTest.java               # Unit tests for TreeRepository
├── pom.xml                                                # Maven configuration file
├── README.md                                              # Project documentation

---

## **How to Run the Application**
Here’s an improved and visually appealing version of the How to Run the Application section for your README.md:

How to Run the Application
Follow these steps to set up and run the application:

1. Clone the Repository
Clone the project to your local machine using the following command:

    git clone <repository-url>
        cd DSA-FINAL-SPIRNT
            cd demo

2. Build the Project
    Use Maven to clean and build the project:

        mvn clean install

3. Run the Application
    Start the Spring Boot application:

        mvn spring-boot:run

4. Access the Application
Once the application is running, open your browser and navigate to the following URLs:

Input Numbers Page:
http://localhost:8080/enter-numbers
Enter a list of comma-separated numbers to create a binary search tree.

Tree Result Page:
After submitting numbers, you will be redirected to the result page, which displays the JSON representation of the tree.

Previously Processed Trees Page:
http://localhost:8080/previous-trees
View all previously processed trees, including their input numbers and JSON structures.

5. Run Tests (Optional)
To verify the functionality of the application, run the unit tests:

6. Stop the Application
To stop the application, press Ctrl + C in the terminal where the application is running.

Testing
The project includes unit tests for the following components:

TreeController:
Tests for /enter-numbers, /process-numbers, and /previous-trees routes.
TreeService:
Tests for creating a balanced binary search tree.
TreeRepository:
Tests for saving and retrieving data.
BinarySearchTree:
Tests for tree insertion logic.
    
    mvn test