# library-android-dragdrop

#### Installation

###### Step 1. Add the JitPack repository to your build file
```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

###### Step 2. Add the dependency
```
dependencies {
    implementation 'com.github.thalesbm:library-android-dragdrop:1.0.0'
}
```
#### How to use?

###### Step 1. Implement the interface OnViewSelection
```
private val callback = object : OnViewSelection {
    override fun viewSelectedPosition(position: Int): Int {
        return position
    }
}
```

###### Step 2. Instance the object DraggableView
```
val draggableView = DraggableView(callback)
```