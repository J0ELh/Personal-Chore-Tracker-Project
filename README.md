# My Personal Project

## Chore Tracker

Functionality Description:

*I want to design an application* that allows users to fairly distribute chores in a household and track the completion
of these chores. Ideally the application could distribute chores based on difficulty
of completing chores. The application would semi-randomly assign people chores based on difficulty,
so that everyone in the household would do their fair share of work.
*This application is to be used by a member of a household* who prefers
a simpler way of ensuring work gets done in the house. Everyone in the household
would benefit from having more transparency of what tasks are to be done and which people
are not doing their fair share of work.

*I am interested in developing this application,* since I believe
that a big reason for conflict in the household is a lack of transparency. If all
members of a household knew how much work everyone else was doing, they would feel responsibility
to do their fair share. The application would also prevent members of a household from feeling
discouraged for doing "all the work" since the application would **show how much others are contributing** and ideally
also **reward those** who always complete their chores by giving them easier work in the future.

  

## User Stories

- As a user, I want to be able to add tasks to my chore tracker
- As a user, I want to be able to add different people to my chore tracker
- As a user, I want to be able to randomly assign tasks to different people
- As a user, I want to be able to see who still hasn't completed their chores
- As a user, I want to be able to save my current chore tracker state to a file
- As a user, I want to be able to be able to load my chore tracker state from file 

## Instructions for Grader

- You can generate the first required event related 
to adding Xs to a Y by adding a Chore to the Chore 
Tracker. To do this, simply click "Add Chore", then 
enter the name of the chore you want to complete and
click "Add." Then, select a difficulty for the chore 
by entering 0, 1, or 2, which stand for "easy," 
"medium," and "hard" accordingly. Hit "Set Difficulty" to 
finish adding the Chore to the Chore Tracker. 
- You can generate the second required event related to adding Xs to a Y by 
adding a Member to the Chore Tracker. To do this,
simply click "Add Member," enter the name, then hit "Add."
- You can locate my visual component by loading up the program. I show a splash screen
with an "animated" loading statement. Essentially, it's just a picture with text to simulate
a loading screen.
- You can save the state of my application by clicking "Save Current Chore Tracker State"
- You can reload the state of my application by clicking "Load Previous Chore Tracker State"
- **BONUS**
  - You can view who hasn't completed their chores by hitting the 4th option of
  the menu. If you click on a specific person, then a pop-up appears with all
  their uncompleted chores.

- **ALTERNATIVELY** you can also do the following actions related to X's and Y's
  - Click "Randomly Assign Chores" to assign all chores that are still
  to be assigned randomly to different members
  - Click "Complete Chore" and enter the name of someone whose chore you want to complete.
  Enter a name from the list of people that pops up on the right panel.
In the next panel, simply enter the chore you want to complete of that person, then
hit "Complete." This will also remove the chore AND/OR the name 
  of that person from the list of members with chore to complete (Option 4)


## Phase 4: Task 2 (Representative Event Log)
Tue Nov 29 12:05:47 PST 2022\
Person: Jonathan added to chore tracker\
Tue Nov 29 12:05:47 PST 2022\
Person: Bill added to chore tracker\
Tue Nov 29 12:05:47 PST 2022\
Person: Barry added to chore tracker\
Tue Nov 29 12:05:47 PST 2022\
Person: Joel added to chore tracker\
Tue Nov 29 12:05:47 PST 2022\
Person: Jason added to chore tracker\
Tue Nov 29 12:05:47 PST 2022\
Person: Jeffrey added to chore tracker\
Tue Nov 29 12:05:47 PST 2022\
Person: Eric added to chore tracker\
Tue Nov 29 12:05:56 PST 2022\
Person: Jona added to chore tracker\
Tue Nov 29 12:06:06 PST 2022\
HARD chore (wash car) added to chore tracker\
Tue Nov 29 12:06:08 PST 2022\
Outstanding chores randomly assigned to different people\
Tue Nov 29 12:06:08 PST 2022\
wash car assigned to Eric\
Tue Nov 29 12:06:19 PST 2022\
chore3 completed by Eric\
Tue Nov 29 12:06:21 PST 2022\
state saved

## Phase 4: Task 3

If I had more time to work on the project, this is the refactoring I would do:

- Simplify the ui button events and shorten the ChoreTrackerMenu class to improve cohesion
  - remove the large batch of poorly named event handler classes
    - this also means getting rid of the inconsistent use of associations and dependencies
  - instead, create a few menu button handler classes grouped by related button events
  - pass/access private fields of the ChoreTrackerMenu to be able to change
  all features **from these classes** instead of calling function back in the ChoreTrackerMenu
  like I'm doing now
- Make the RightInteractionPanel easier to understand by writing clearer methods
and allowing for different cases of inputs (overriding methods)
  - make InteractionButton, PromptField, and inputArea (a specific JTextArea)
  part of RightInteractionPanel so that ChoreTrackerMenu doesn't have to deal with
  controlling that behaviour
- Renaming Person to something better representing a member of a household or
dorm room
