fun readFile(filename: String): String = object {}.javaClass.getResource(filename)!!.readText()
