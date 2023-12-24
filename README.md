# Course-Scheduler

This project involves the development of a Course Scheduling application for a college, aimed at facilitating the scheduling of courses by semester. The application will have a user-friendly GUI and will be driven by a Derby database. Emphasis is placed on Object-Oriented Design and Programming, with a requirement for at least four classes besides the main GUI class.

# Admin Functions (Implemented in Part 1):

    Add Semester: Add a semester to the database, identified by a name.
    Add Course: Add a new course to the database, identified by a code and description.
    Add Class: Add a new class to the database, identified by the semester, course code, and maximum number of students.
    Add Student: Add a student to the database, identified by a studentID, first name, and last name.

# Student Functions (Implemented in Part 1):

    Schedule Class: Schedule a student in a class for a specified semester, considering seat availability. If no seats are available, the student is waitlisted.
    Display Schedule: Display the current schedule of classes for a specified student in the current semester, showing course code and status (scheduled or waitlisted).
    Display Classes: Display a complete list of classes for the current semester, showing course code, description, and number of seats.

# GUI Guidelines:

    Use drop-down lists (Combo Boxes) for known data.
    Display all requested information without requiring additional commands.
    Results of commands should be displayed immediately on the same interface.
