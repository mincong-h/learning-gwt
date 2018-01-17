# learning-gwt

Learning GWT

## GWT Tutorial

See <http://www.gwtproject.org/doc/latest/tutorial/index.html>

Create a new application called _"StockWater"_:

```
$ ./webAppCreator -out StockWatcher \
    com.google.gwt.sample.stockwatcher.StockWatcher
```

## GWT Con 2016 Keynote

See <https://www.youtube.com/watch?v=P4VhPck5s_g>

There're many interesting ideas out there. Let's take a look together:

- GWT 2.8 will be a long time support.
- GWT 2.8 supports Java 8.
- GWT transpiles Java to JavaScript, but no longer optimizing it.
- Optimization will be done by [Closure compiler](https://github.com/google/closure-compiler)
- Transpiling Java to JavaScript can use either GWT or J2CL.
- J2CL means Java-to-Closure, aka Java to Closure compiler.
- J2CL is used by Gmail, Google Doc etc.
- J2CL is still experimental and thus close source.
- Closure compiler use type-system on top of JavaScript system.
- Closure compiler is a JavaScript to (better) JavaScript compiler.
- Closure compiler uses JavaScript comment and annotation type inference.
- Closure compiler supports generic, inheritance, polymorphism and more.
- Making closure compiler better is not only benifit for Java user, but also
  JavaScript users. For examples, better integration for Polymer, Angular, React
  frameworks.
- Depending on the browser types and version, GWT transpiles to different
  JavaScript. GWT might use ES5, ES6 for better performance.
- GWT uses annotations, like `@JsType`.
- GWT supports different levels of type-check: normal (default), optimized,
  minimal, none. Depends on quality of the codebase, and the runtime execution
  requirements, you can choose different type-checks.
