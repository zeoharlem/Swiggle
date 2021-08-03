package ng.com.zeoharlem.swopit

class Carrier(val tyre: Int, val doors: Int, val model: String) {

    var capacity: Int   = 0
    constructor(engineType: String, tyre: Int, doors: Int, model: String): this(tyre, doors, model){
        if(model.equals("lexus")){
            capacity    = doors * tyre * 2;
        }
    }
}