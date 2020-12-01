<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        version="1.0">
    <xsl:output method="html" indent="yes" />

    <xsl:template match="/">
        <textarea rows="12" cols="60" style="border:none;resize:none;">
            <post>
                <Title>
                    <xsl:value-of select="post/title" />
                </Title>
                <Username>
                    <xsl:value-of select="post/username" />
                </Username>
                <Message>
                    <xsl:value-of select="post/message" />
                </Message>
                <Date>
                    <xsl:value-of select="post/date" />
                </Date>
                <Group>
                    <xsl:value-of select="post/postGroup" />
                </Group>
                <xsl:choose>
                    <xsl:when test="post/fileName">
                        <Attachment>
                            <FileName>
                                <xsl:value-of select="post/fileName" />
                            </FileName>
                            <FileSize>
                                <xsl:value-of select="post/fileSize" />
                            </FileSize>
                            <MediaType>
                                <xsl:value-of select="post/mediaType" />
                            </MediaType>
                        </Attachment>
                    </xsl:when>
                    <xsl:otherwise>
                        <Attachment>
                            <xsl:value-of select="post/attachment" />
                        </Attachment>
                    </xsl:otherwise>
                </xsl:choose>
            </post>
        </textarea>
    </xsl:template>
</xsl:stylesheet>