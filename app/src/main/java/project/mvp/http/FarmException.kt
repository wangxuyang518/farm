package project.mvp.http

public class FarmException constructor(string: String, code: Int) : Exception(string) {

    public lateinit var msg: String
    public var code: Int

    init {
        this.msg = string
        this.code = code
    }

}