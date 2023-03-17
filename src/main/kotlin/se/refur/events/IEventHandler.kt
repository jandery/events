package se.refur.events

fun interface IEventHandler {
    fun handle(vararg args: Any)
}
