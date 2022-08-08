# JsonPlaceHolderPostSaver
This is simple scala application developed to download all posts from JsonPlaceHolder `https://jsonplaceholder.typicode.com/posts`
Each post is saved to separate file as `<post_id>.json`. By default, all files are saved in project directory.

## How to run the code
Project is built with `sbt`, so to run it type:

````
sbt run
````
or

````
sbt "run <directory_path>"
````

in command line. As you can see `<directory_path>` is optional argument used to specify directory for saving posts. 
If given directory does not exist files will be saved in default project folder.

## External dependencies

As external dependencies project uses:
 * `Requests-Scala` library for Rest API requests. This is the Scala version of well-known `Requests` Python library.
 * `json4s` for dealing with json parsing
 * `ScalaMock` for mocking objects in tests
