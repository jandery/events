package se.refur.events

interface IEventHandler {
    fun handle(vararg args: Any)
}