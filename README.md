# Learning GWT [![Build Status][travis-img]][travis]

Learning GWT (Google Web Toolkit).

## GWT Tutorial

Build a sample GWT application by following the
[GWT tutorial](http://www.gwtproject.org/doc/latest/tutorial/index.html) and
[Maven GWT plugin](https://gwt-maven-plugin.github.io/gwt-maven-plugin/user-guide/archetype.html).
Firstly, create a new application using the archetype of GWT Maven plugin.
The group ID, artifact ID and other metadata will be asked in interactive mode:

```
$ mvn archetype:generate \
    -DarchetypeGroupId=org.codehaus.mojo \
    -DarchetypeArtifactId=gwt-maven-plugin \
    -DarchetypeVersion=2.8.1
```

Then run the GWT application in DevMode via Maven:

```
$ mvn gwt:run
```

## GWT Browser Support

Here's a list of GWT `user.agent` properties and the browsers they correspond
to (inspired by [this post](https://stackoverflow.com/questions/16047427/)). The
property are defined by [`UserAgent.gwt.xml`][UserAgent.gwt.xml]:

```xml
<!-- Browser-sensitive code should use the 'user.agent' property -->
<define-property name="user.agent" values="ie8" />
<extend-property name="user.agent" values="gecko1_8" />
<extend-property name="user.agent" values="safari" />
<extend-property name="user.agent" values="ie9" fallback-value="ie8" />
<extend-property name="user.agent" values="ie10" />
<property-provider name="user.agent" generator="com.google.gwt.useragent.rebind.UserAgentPropertyGenerator"/>
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
- Making closure compiler better is not only benefit for Java user, but also
  JavaScript users. For examples, better integration for Polymer, Angular, React
  frameworks.
- Depending on the browser types and version, GWT transpiles to different
  JavaScript. GWT might use ES5, ES6 for better performance.
- GWT uses annotations, like `@JsType`.
- GWT supports different levels of type-check: normal (default), optimized,
  minimal, none. Depends on quality of the codebase, and the runtime execution
  requirements, you can choose different type-checks.

## GWT Compiler

The GWT compiler is defined in `$GWT_HOME/dev`.

- **com.google.gwt.core.ext** external resources?
- **com.google.gwt.core.linker** for linking resources.
- **com.google.gwt.dev.cfg** defines mechanism for configurations, coming from
  GWT files _"\*.gwt.xml"_, dynamic Oracle property etc.
- **com.google.gwt.dev.javac** provides support for `javac`, Java programming
  language compiler.
- **com.google.gwt.dev.jdt** provides support for Eclipse Java development tools
  (JDT compiler), which behaves differently from `javac` (not sure)
- **com.google.gwt.dev.js** JavaScript package, including _ast_ for abstract
  syntax tree, _globles_ for browser globles, and _rhino_ for source
  imported from Mozilla's Rhino project.
- **com.google.gwt.dev.jjs** Java-to-JavaScript (jjs) compiler. Its sub-package
  _ast_ contains the Java classes for abstract syntax tree (ast) construction.
  Its sub-package _impl_ contains the referenced implementation.
- **com.google.gwt.dev.json** special JSON handling.
- **com.google.gwt.dev.resource** TODO
- **com.google.gwt.dev.shell** GWT shell
- **com.google.gwt.dev.ui** different UI and events.
- **com.google.gwt.dev.url** uniform resource loading, e.g. JAR.
- **com.google.gwt.dev.util** utility classes.
- **com.google.gwt.util** is a module contains utility classes. Its sub-package
  _regexfilter_ is useful for defining regex filter for inclusion or exclusion.
  Its sub-package _tools_ is useful for handle GWT command-line applications,
  e.g. different kinds of argument handling.
- **com.google.gwt.soyc** is the story of your compile (SOYC). To obtain a SOYC
  report for your application, you need to add flag `-soyc` to the compilation
  options, which will generates a XML result; then convert the result into
  viewable HTML.

## Terminology

Abbreviation | Description
:--- | :---
DOM | Document Object Model

- **Deferred binding** is a feature of the GWT compiler that works by generating many versions of code at compile time, only one of which needs to be loaded by a particular client during bootstrapping at runtime. Each version is generated on a per browser basis, along with any other axis that your application defines or uses.

[travis]: https://travis-ci.org/mincong-h/learning-gwt
[travis-img]: https://travis-ci.org/mincong-h/learning-gwt.svg?branch=master
[UserAgent.gwt.xml]: https://github.com/gwtproject/gwt/blob/master/user/src/com/google/gwt/useragent/UserAgent.gwt.xml

## References

- [How to use GWT 2.0 with Maven and Generate SOYC Reports][1]

[1]: https://raibledesigns.com/rd/entry/how_to_use_gwt_2
