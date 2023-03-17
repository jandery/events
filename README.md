# events

The purpose of this project is a common way of handling events


##### Getting started
Include the following in your POM:
```xml
<project xmlns="...">
    <repositories>
        <repository>
            <id>maven-central</id>
            <url>https://repo.maven.apache.org/maven2</url>
        </repository>
        <repository>
            <id>maven-repo.refur.se</id>
            <url>https://s3-eu-west-1.amazonaws.com/maven-repo.refur.se/release</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>se.refur</groupId>
            <artifactId>events</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>    
</project>
```

##### Implementing events and event manager

```kotlin
package se.refur.examples

// Events for decisions
enum class DecisionEventsEnum : IEvent {
    APPROVED, REJECTED
}

// Events for state changes
enum class StateChangeEventsEnum : IEvent {
    FIRST_STATE, SECOND_STATE
}

// Manager for handling events for Decisions
val decisionManager = EventManager<DecisionEventsEnum>()
// Manager for handling events for State changes
val stateChangeManager = EventManager<StateChangeEventsEnum>()
```

##### Registering events

```kotlin
package se.refur.examples

decisionManager.register(DecisionEventsEnum.APPROVED, object : IEventHandler {
    override fun handle(vararg args: Any) {
        println("args size in handle ${args.size}")
    }
})
stateChangeManager.register(StateChangeEventsEnum.FIRST_STATE, object : IEventHandler {
    override fun handle(vararg args: Any) {
        println("args size in handle ${args.size}")
    }
})
```

##### Firing events

```kotlin
package se.refur.examples
// with arguments
decisionManager.notify(DecisionEventsEnum.APPROVED, "GUID1", 42)
// without arguments
stateChangeManager.notify(StateChangeEventsEnum.FIRST_STATE)
```