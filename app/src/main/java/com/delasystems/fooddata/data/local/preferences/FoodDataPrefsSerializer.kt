package com.delasystems.fooddata.data.local.preferences

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.delasystems.fooddata.FoodDataPrefs
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object MyPrefsSerializer : Serializer<FoodDataPrefs> {
    override val defaultValue: FoodDataPrefs = FoodDataPrefs.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): FoodDataPrefs {
        try {
            return FoodDataPrefs.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: FoodDataPrefs,
        output: OutputStream
    ) = t.writeTo(output)
}

