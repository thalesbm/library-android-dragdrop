package bm.it.mobile.library

interface OnViewSelection {

    fun viewSelectedByPosition(items: HashMap<Int, Int>)

    fun viewSelectedByTag(items: HashMap<String, String>)
}
