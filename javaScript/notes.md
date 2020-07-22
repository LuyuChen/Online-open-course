[JavaScript essential training](https://www.linkedin.com/learning/javascript-essential-training-3/variables-the-catch-all-containers-of-javascript?u=0)

Available at LinkedIn Learning

2.3 Add Inline JavaScript to a HTML
  HTML file was executed from top to bottom. If you want to add script in the head when trying to use the element in the body, you need to add `defer` syntax
  ```javascript
    <head>

    </head>

    <body>

    <body>
  ```
  ### 3. Working with data
3.1 The catch-all containers of JavaScript
  - to avoid global scope, always declare your variables

3.2 Data types in JavaScript
  - 6 primitive types: Numeric, String, Boolean, null, undefined, Symbol
  - escape sequence in string \"
  - javascript is weakly typed language
  ```javascript
    console.log(typeof variable)
  ```

3.3 Arithmetic operators and math
  - Unary operator: ++, --
  - a++: grab the original value, then increment 1
  ```javascript
    let a = 1;
    console.log(++a): 2
    console.log(a++): 2
    a: 3
  ```

3.4 Working with String and numbers
  ```javascript
    let a = 4;
    let b = "5";
    let sum = a + b;
    sum: "45"
  ```
  -- Nan: try to use arithmetic operator on non-numeric value

3.5 & 3.6 Conditional statements and logic
  - ==, ===(also check the type)
  - &&, ||
  - ternary operator: condition ? true statement1 : false statement2

3.7 && 3.8 Arrays
  - array elements can be different types
  - arrays are objects
  - arrays properties and methods:
    ```javascript
      let pens = [1,2,3,4]
      pens.length; // 4
      pens.reverse(); // [4,3,2,1]
      pens.shift() // [2,3,4]
      pens.unshift(1); // [1,2,3,4]
      pens.pop(); //[1,2,3]
      pens.push(5); //[1,2,3,4,5]
      pens.splice(2, 1); // [1,2,4]
      pens.slice(); // create a new array copying the same elements from the old array
      pens.indexOf(1, 0); // 0, take two arguments, first one is the element you want search for, the second is where you want to start search for
      pens.join(separator); // "1,2,3,4", the argument seperator can be customized, default one is ','
    ```

  ### 4 Functions and Objects
4.1 Functions in JavaScript
  - three types of functions: 1. names functions 2. anonymous functions 3. immediately invoked function expressions
  - named functions:
    ```javascript
      function theBiggest() {

      }
    ```
  - anonymous functions:
     ```javascript
      let the Biggest = function(a, b) {

      }

      theBiggest(a, b);
    ```
  - immediately invoked functional expressions:
  The function below will be immediate invoked when encountered.
  You need to define the argument variable before the function in order to use them
  ```javascript
    let theBiggest = (function(a, b) {
          let result;
          a > b ? result = ['a'] : result = ['b'];
          return result;
        })(4, 3);
    
    console.log(theBiggest);
  ```

4.7 & 4.8 variable scope
  - global scope / local scope
  - ES2015: let / const:
    - const: constant, cannot be changed once defined
    - let: block scope variable, smaller scope than var
    ```javascript
      function logScope() {
        var localVar = 2;
        if (localVar) {
          var localVar = 'I am differet';
          console.log(localVar);
        }

        console.log(lovalVar);
      }

      logScope();
      // all console.log will print out 'I am different'
      // To have block scope variable, use let
    ```

4.9 make sense of objects
  ```javascript
    let course = new Object();
    course.title = "JavaScript Essential Training";
    course.instructor = "Morten Rand-Hendriksen";

    //
    let course = {
      title: "JavaScript Essential Training",
      instructor: "Morten Rand-Hendriksen",
      views: 0,
      updateViews: function() {
        retirn ++course.views;
      }
    }
  ```
  - object constructors:
  ```javascript
  // object constructors
    function Course(title, instructor, level, published, views) {
      this.title = title;
      this.instructor = instructor;
      this.level = level;
      this.published = published;
      this.views = views;
      this.updateViews = function() {
        return ++this.views;
      }
    }

    let course01 = new Course('javascript essential training', 'Morten', 1, true, 0);
    let course02 = new Course('up and running with ECMAScript6', 'eve', 1, true, 123456);
  ```

  - dot notation / bracket notation
  ```javascript
    course.title / course['title']
    // why we have bracket notation? 
    // sometimes object passing from other objects or servers have weird property names
    course['wp:image']
  ```

4.11 Closures
  - What is closure?
    - A function inside a function that replies on variables in outside function to work
    ```javascript
      function doSomeMath() {
        let a = 4;
        let b = 5;

        let multiply = function() {
          let result = a * b;
          return result;
        }

        return multiply;
      }

      let theResult = doSomeMath();
      console.log('closure example', theResult());
      // without closeure, theResult() should give us an error since a & b, both variables are not defined for now
    ```
  - why do we have this feature?
    ```javascript
      function giveMeEms(pixels) {
        var baseValue = 16;

        function doTheMath() {
          return pixels / baseValue;
        }

        return doTheMath;
      }

      var smallSize = giveMeEms(12);
      var mediumSize = giveMeEms(18);
      var largeSize = giveMeEms(24);
      var xlargeSize = giveMeEms(32);

      console.log("Small size: ", smallSize());
      console.log("medium size: ", mediumSize());
      console.log("large size: ", largeSize());
      console.log("xlarge size: ", xlargeSize());
    ```
  - reference:
    [closure on MDN](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Closures)

  ### JavaScript and the DOM
  5.1 DOM: The document object model
    - Browser object model: current window, it contains the document
    - Node tree, document is the root node
  
  5.2 target elements in the DOM with querySelector methods
    - document.body, document.title, document.URL
    ```javascript
    document.getElementById()
    document.getElementsByClassName();
    document.getElementsByTagName();

    // get the first match elements
    document.querySelector(" ");
    // get all matching elements
    document.querySelectorAll("");
    // . for CSS selector; # for id selector
    ```

  5.3 Access and change elements, classes, attributes
  [References on MDN](https://developer.mozilla.org/en-US/docs/Web/API/Element)
    ```javascript
      document.querySelector(".main-title").innerHTML;
      document.querySelector(".main-title").className;
      document.querySelector(".main-title").classList;

      element.getAttribute("attr name");
      element.setAttribute("attrName", "value");
    ```
  
  5.6 add DOM elements && add inline CSS to an element
    - inline CSS: add any CSS property to any element via Javascirpt using the style attribute
    - inline css overrides whatever CSS is applied to an element
    - in mose cases, best practice is to create CSS rules and use JavaScript to manage these classes to apply the rules to the element

  ```javascript
  // style is one of types of attribute, 
    document.querySelector(".cta a").style.color;

    document.querySelector(".cta a").style.cssText = "padding: 1em; color: red";

    document.querySelector(".cta a").setAttribute("style", "padding: 2em; color: green; background-color: black");
  ```









