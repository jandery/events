package se.refur.events

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EventHandlerAsClassTest {
    private enum class EventsEnum : IEvent {
        FIRST_EVENT,
        SECOND_EVENT,
        THIRD_EVENT
    }

    private class Logger {
        val log: MutableList<String> = mutableListOf()

        fun logText(text: String) {
            log.add(text)
        }
    }

    private class LogEvent(private val logger: Logger) : IEventHandler {
        override fun handle(vararg args: Any) {
            logger.logText("Event is logged with args '${args.joinToString(",")}'")
        }
    }

    private class SaveEvent(private val logger: Logger) : IEventHandler {
        override fun handle(vararg args: Any) {
            logger.logText("Event is saved with args '${args.joinToString(",")}'")
        }

    }

    private fun generateEventManager(logger: Logger): EventManager<EventsEnum> = EventManager<EventsEnum>().also {
        it.register(EventsEnum.FIRST_EVENT, SaveEvent(logger))
        it.register(EventsEnum.FIRST_EVENT, LogEvent(logger))
        it.register(EventsEnum.SECOND_EVENT, SaveEvent(logger))
        it.register(EventsEnum.SECOND_EVENT, LogEvent(logger))
    }

    @Test
    fun notify_multipleEventsRegisteredOnlyOneIsFired_sizeIs2() {
        val logger = Logger()
        val eventManager = generateEventManager(logger)
        eventManager.notify(EventsEnum.FIRST_EVENT, "First event")
        assertThat(logger.log).hasSize(2)
            .contains("Event is saved with args 'First event'")
            .contains("Event is logged with args 'First event'")
    }

    @Test
    fun notify_noArgument_sizeIs2() {
        val logger = Logger()
        val eventManager = generateEventManager(logger)
        eventManager.notify(EventsEnum.FIRST_EVENT)
        assertThat(logger.log).hasSize(2)
            .contains("Event is saved with args ''")
            .contains("Event is logged with args ''")
    }

    @Test
    fun notify_multipleArguments_sizeIs2() {
        val logger = Logger()
        val eventManager = generateEventManager(logger)
        eventManager.notify(EventsEnum.FIRST_EVENT, "First", 2, false)
        assertThat(logger.log).hasSize(2)
            .contains("Event is saved with args 'First,2,false'")
            .contains("Event is logged with args 'First,2,false'")
    }

    @Test
    fun notify_multipleEventsRegisteredNonRegisteredIsFired_sizeIs0() {
        val logger = Logger()
        val eventManager = generateEventManager(logger)
        eventManager.notify(EventsEnum.THIRD_EVENT, "First event")
        assertThat(logger.log).hasSize(0)
    }
}