package se.refur.events.sample

import se.refur.events.EventManager
import se.refur.events.IEvent
import se.refur.events.IEventHandler

/**
 * Events for decisions
 */
enum class DecisionEventsEnum : IEvent {
    APPROVED,
    REJECTED,
    REVERTED
}

val decisionManager = EventManager<DecisionEventsEnum>()

fun main() {
    decisionManager.register(DecisionEventsEnum.APPROVED, object : IEventHandler {
        override fun handle() {
            println("Handle approved event")
        }
    })
    decisionManager.register(DecisionEventsEnum.APPROVED, object : IEventHandler {
        override fun handle() {
            println("Log approved event")
        }
    })
    decisionManager.register(DecisionEventsEnum.REJECTED, object : IEventHandler {
        override fun handle() {
            println("Handle rejected event")
        }
    })

    decisionManager.notify(DecisionEventsEnum.APPROVED)
}