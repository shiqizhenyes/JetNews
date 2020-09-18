package me.nice.jetnews.model

import androidx.compose.ui.graphics.ImageAsset

data class Post(
        val id: String,
        val title: String,
        val subtitle: String,
        val url: String,
        val publication: Publication,
        val metaData: MetaData,
        val paragraphs: List<Paragraph> = emptyList(),
        val imageId: Int,
        val imageThumbId: Int,
        val image: ImageAsset? = null,
        val imageThumb: ImageAsset? = null
)

data class MetaData(
        val author: PostAuthor,
        val date: String,
        val readTimeMinutes: Int
)

data class PostAuthor(
        val name: String,
        val url: String
)

data class Publication(
        val name: String,
        val logoUrl: String
)

data class Paragraph(
        val type: ParagraphType,
        val text: String,
        val markups: List<Markup> = emptyList()
)

data class Markup(
        val type: MarkupType,
        val start: Int,
        val end: Int,
        val href: String? = null
)

enum class MarkupType{
    Link,
    Code,
    Italic,
    Bold
}

enum class ParagraphType {
    Tile,
    Caption,
    Header,
    Subhead,
    Text,
    CodeBlock,
    Quote,
    Bullet
}


