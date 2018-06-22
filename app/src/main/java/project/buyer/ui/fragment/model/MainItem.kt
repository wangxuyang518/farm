package project.buyer.ui.fragment.model

import project.farm.R

public class MainItem {
    public var name: String = ""
    public var icon: Int = R.mipmap.ic_launcher

    constructor(name: String, icon: Int) {
        this.name = name
        this.icon = icon
    }

    constructor()
}