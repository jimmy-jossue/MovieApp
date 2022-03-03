package dev.jgm.movieapp.data.repository

import dev.jgm.movieapp.data.network.models.VideoModel
import dev.jgm.movieapp.domain.model.Video

fun Video.toModel(): VideoModel {
    return VideoModel(
        id = id,
        key = key,
        name = name,
        site = site,
        size = size,
        type = type
    )
}

fun VideoModel.toVideo(): Video {
    return Video(
        id = id,
        key = key,
        name = name,
        site = site,
        size = size,
        type = type
    )
}