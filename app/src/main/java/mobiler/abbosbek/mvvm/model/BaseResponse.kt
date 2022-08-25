package mobiler.abbosbek.mvvm.model

data class BaseResponse <T>(
    val success : Boolean,
    val message : String,
    val error_code : String,
    var data : T
)
