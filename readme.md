
# Smart-Journaling

A simple app to help you journal and keep track of your thoughts.

**Yunnan University, Malaya College, Artificial Intelligence, Programming Class 1**

---
## Technical Documentation Implementation Considerations:
1. Create a User entity class to store user information, such as username, password, and email.
2. Create a Note entity class to store note information, such as title, content, creation time, update time, user ID, etc.
3. Create a Note service class to provide CRUD (Create, Read, Update, Delete) functionality for notes.
4. Create a User service class to provide basic user registration and login functionality.
5. Create a Note controller class to handle user requests for notes.
6. Implement a storage control class for storing note data. Based on various data input by the user, the code will perform local storage using input and output streams without connecting to a database. The storage format is mainly divided into two types: TXT files store the full content of the note, and JSON files store the note's title, content, creation time, update time, user ID, and other information.

---
### Project Implementation Preliminary Idea: Create a function that supports users in locally recording, creating, deleting, modifying, and querying notes.

---
## Initial Implementation Plan:
1. User operations (login and logout) are performed in the command line.
2. Note input (title, content) is performed in the command line. Notes are stored in TXT files, and note metadata is saved in JSON files.
3. When a note is called by the user, the TXT file needs to be read, and the JSON file needs to be parsed to display the note content to the user.
4. While writing a note, the user can choose to press the "esc" key to save the note and return to step 4.
5. The interface from a new user's registration to note implementation is as follows, all conducted in the command line:
   1. Start interface: Call an API to display the date ![img_3.png](img_3.png), simultaneously presenting two options: 1. Login 2. Register.
   2. Login option: Input email on one line, password on the next line. Perform login verification using stored user information. If the user is not found in the data, output "User does not exist".
   3. Register option: Input username on one line, email on the next line, password on the next line. Store user information in the data and output "Registration successful".
   4. Both step 2 and step 3 jump to step 4, whose content is the note service display.
   5. Note services are: 1. Create Note 2. Delete Note 3. Modify Note 4. View Note 5. Exit. For note content, first input the title, then the content. The system should automatically record the creation date and time.
   6. Additionally, while writing a note, the user is allowed to choose to exit. If the user presses the "esc" key, the note content is saved in a TXT file, the note metadata is saved in a JSON file, and the system returns to step 4.
### (ง๑ •̀_•́)ง Next, I will add the part about calling APIs to analyze the notes.
### (,,・ω・,,) The date greeting function has been added.
# Thanks for your attention. d(`･∀･)b