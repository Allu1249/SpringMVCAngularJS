INTRO
======
This project uses spring mvc for retrieving data
for a Single Page Application based on angularjs

The app will be based on the todo list tutorial but with a backend to it and
who knows what else i'll be doing with it..


TODO
=====
*SERVER*

1. ~~create TODO entity + modify ddl and dml initialisation (see resources/db)~~
2. ~~create service for CRUD of todo using spring data~~
*which actually does the work for you ... yeay*
3. ~~create a TodoController to accept ajax calls for CRUD operaitons on the TODO object
triggered from javascript.~~ TODO need to add other operations but basic plumbing is done
4. ... unit & integration tests ...

*CLIENT*

1. ~~Create the TODO app in single html file WEB-INF/pages/todo.html~~
2. ~~Make index page that links to the todo.html page this page will link to all test pages~~
3. Add ajax functionality to the todo.html to retrieve and update/store items on the serverside.
