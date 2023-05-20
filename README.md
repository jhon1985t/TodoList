Android Clean Architecture TODO LIST
==================================

A project providing demonstrations on how to architect an Android app using Uncle Bob's Clean Architecture approach.

Solid Desing Principles
==================================

A pattern that helps to create code more independent and reusable

Samples
-------

| Sample                             | Description                                                                |
| (mvvm)                             | A clean Model-View-ViewModel (MVVM) architecture sample written in kotlin.   |
| (solid)                            | A clean Design Pattern written in Kotlin.   |
| (hilt)                             | A clean Independency Injection Graph in Kotlin.   |
| (room)                             | A clean DB local.   |

Sample app overview
-------------------

A simple app for create a local list, see detail list, delete a save lists, delete all lists.

Clean Architecture create modules or librarys that are independent from the app main module, following
the definition of layers or entities(each layers or entity have a responsability)

DATA : Content the connections to apis, bds, preferences
USECASES : Content the connections between the data(remote, local) to the view
DOMAIN : Content models
APP : Has vision to all layers and use it

MVVM : MVVM pattern architecture suggested by Google, connect the usecases, models, data with the view,
the view receive a simple object or response to work

Run the app
--------------------

Clone the project from github, once cloned, use a smart device or emulator, run the app and wait for launch the view
Main View = load all the comments, with an option to delete all the comments that are not favorites
Detail View = load a particular comment, with option to save as favorite, option to delete the current comment

CI/CD
--------------------

Added CI/CD with Github/Actions

General architecture
--------------------

![General architecture](https://miro.medium.com/v2/resize:fit:1400/format:webp/1*cgJSwLqnHvY5nrhdu4AsFA.jpeg)

JHON JAROL TABARES OROZCO