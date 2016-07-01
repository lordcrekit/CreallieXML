# CreallieXML
* Simplified XML reader, writer, and data structure.
* Designed for working with purely hierarchical data.
* Exactly preserves data written in it. When reading outside xml, ignores whitespace used for formatting.

## Data
* Unlike normal HTML, the textual data of every element and property may be made up of only pure text.
* Elements may store children, but not as part of their textual content.

## Searching
* To easily navigate the XML tree structure, you use objects called Filters which are passed to the ```getChildren()```/```getProperty()``` functions.
* Filters may be combined, and there are filters for looking at the child elements/properties. 
* These filters can take their own filters, recursivly.

######Getting a child element with name "Factor" and value "32"
```java
CreaElement rootElement = doc.getRootElement();
CreaElement child = rootElement
    .getChild(
      new NameFilter("Factor"),
      new ValueFilter("32")
    );
```

######Getting a child element with the name "Factor" and the Property type="basic"
```java
CreaElement rootElement = doc.getRootElement();
CreaElement child = rootElement
    .getChild(
      new NameFilter("Factor"),
      new HasPropertyFilter(
        new NameFilter("type"),
        new ValueFilter("basic")
      )
    );
```

## Null Elements
* Because CreallieXML is designed for the shortest possible code, asking for a child which does not exist will return a special case element or property.
* This special case has ```null``` for its name and value.
* Asking for children/properties will return an empty list
* Since it has no children, asking for a child/property will return the special case again.
* As a result, you will always avoid ```NullPointerException```s.
* To see if the element/property actually exists, use the ```exists()``` function.

######See if the root has a child named "Factor"
```java
CreaElement rootElement = doc.getRootElement();
boolean exists = rootElement
  .getChild(
    new NameFilter("Factor")
  ).exists();
```

## Whitespace policy
* I have to write this still. After I've done the unit tests, this will be updated.
