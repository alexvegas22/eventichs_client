import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.Base64


@RequiresApi(Build.VERSION_CODES.O)
fun requestAccessTokenWithPassword(
    clientId: String = "lWikwLG2UkMXoCvLoa4z7AGARWOCPZiv",
    clientSecret: String = "5V14hP1livmhQXXF1v7p906YhD8q9FLRUbyKvyaFdrzLw_B3PCSmzuz8QFI_COWC",
    username: String = "kierangreene@gmail.com",
    password: String = "kieran_greene123"
): String {
    val tokenEndpoint = "https://dev-xa2f6qcrb7kap33n.us.auth0.com/oauth/token"

    val client = OkHttpClient()

    val requestBody = FormBody.Builder()
        .add("grant_type", "password")
        .add("username", username)
        .add("password", password)
        .build()

    val request = Request.Builder()
        .url(tokenEndpoint)
        .addHeader("Authorization", "Basic ${Base64.getEncoder().encodeToString("$clientId:$clientSecret".toByteArray())}")
        .post(requestBody)
        .build()

    val response = client.newCall(request).execute()

    return response.body?.string() ?: ""

}
