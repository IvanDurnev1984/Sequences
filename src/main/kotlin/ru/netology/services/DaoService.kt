package ru.netology.services

class DaoService<T> {
    private var id: Int = 0
    private var showMap = mutableMapOf<Int, T>()
    private var delMap = mutableMapOf<Int, T>()


    fun create(t: T) {
        showMap.put(id++, t)
    }

    fun read(): List<T> {
        return showMap.values.map{ x -> x}
    }

    fun readById(id: Int): T? {
        return showMap[id]
    }

    fun delete(id: Int) {
        showMap[id]?.let { delMap.put(id, it) }
        showMap.remove(id)
    }

    fun update(id: Int, t: T) {
        showMap.put(id, t)
    }

    fun restore(id: Int) {
        delMap[id]?.let { showMap.put(id, it) }
        delMap.remove(id)
    }
}