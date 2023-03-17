package se.refur.events.sample

import se.refur.events.EventManager
import se.refur.events.IEvent

/**
 * Events for decisions
 */
enum class DecisionEventsEnum : IEvent {
    APPROVED,
    REJECTED
}

// Manager for handling events for Decisions
val decisionManager = EventManager<DecisionEventsEnum>()

fun main() {
    decisionManager.register(DecisionEventsEnum.APPROVED) { args ->
        println("Handle approved event, args size ${args.size}")
    }
    decisionManager.register(DecisionEventsEnum.APPROVED) { _ ->
        println("Log approved event")
    }
    decisionManager.register(DecisionEventsEnum.REJECTED) { _ ->
        println("Handle rejected event")
    }
    decisionManager.notify(DecisionEventsEnum.APPROVED, "GUID1", 42)
}