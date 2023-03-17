package se.refur.events

/**
 * The purpose of this class is to manage events
 */
class EventManager<T : IEvent> {
    private val listeners: MutableMap<IEvent, MutableList<IEventHandler>> = mutableMapOf()

    fun register(event: T, handler: IEventHandler) {
        val listener = listeners.getOrPut(event) { mutableListOf() }
        listener.add(handler)
    }

    fun notify(event: T) {
        listeners[event]?.asSequence()
            ?.forEach { it.handle() }
    }
}