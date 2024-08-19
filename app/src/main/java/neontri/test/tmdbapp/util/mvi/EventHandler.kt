package neontri.test.tmdbapp.util.mvi

fun interface EventHandler<in T : Event> {
  fun obtainEvent(event: T)
}