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
        override fun handle(vararg args: Any) {
            println("args size in handle ${args.size}")
            println("Handle approved event ${args[0]}, ${args[1]}")
        }
    })
    decisionManager.register(DecisionEventsEnum.APPROVED, object : IEventHandler {
        override fun handle(vararg args: Any) {
            println("args size in handle ${args.size}")
            println("Log approved event")
        }
    })
    decisionManager.register(DecisionEventsEnum.REJECTED, object : IEventHandler {
        override fun handle(vararg args: Any) {
            println("Handle rejected event")
        }
    })

    decisionManager.notify(DecisionEventsEnum.APPROVED, "GUID1", 42)
}