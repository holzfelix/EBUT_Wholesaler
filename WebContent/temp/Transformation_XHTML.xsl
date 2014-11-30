<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:output method="xhtml"/>
	<xsl:template match="/">
		<html>
			<head>
				<title>Produktkatalog</title>
			</head>
			<p>TEST: <xsl:value-of select="BMECAT/TEST"/></p>
			<xsl:apply-templates select="BMECAT/HEADER"/>
		</html>
	</xsl:template>
	<xsl:template match="BMECAT/HEADER">
		<body>
			<h1><xsl:value-of select="CATALOG/CATALOG_NAME"/></h1>
			<p>Cataloge ID: <xsl:value-of select="CATALOG/CATALOG_ID"/></p>
			<p>Cataloge Version: <xsl:value-of select="CATALOG/CATALOG_VERSION"/></p>
			<p>Cataloge language: <xsl:value-of select="CATALOG/LANGUAGE"/></p>
			<p>Lieferant: <xsl:value-of select="SUPPLIER/SUPPLIER_NAME"/>
			</p>
			<table>
				<thead>
					<tr>
						<th>Artikelnummer</th>
						<th>Kurzbeschreibung</th>
						<th>EAN</th>
						<th>Langebschreibung</th>
						<th>Preistyp</th>
						<th><table>
							<tr>
								<th>Preis (Betrag)</th>
								<th>Länderkürzel</th>
								<th>Währung</th>
								<th>Steuersatz</th>
							</tr>
						</table></th>
					</tr>
				</thead>
				<tbody>
					<xsl:apply-templates select="../T_NEW_CATALOG"/>
				</tbody>
			</table>
		</body>
	</xsl:template>
	<xsl:template match="BMECAT/T_NEW_CATALOG">
	<xsl:apply-templates select="ARTICLE"/>
	</xsl:template>
	<xsl:template match="ARTICLE">
			<tr>
				<td><xsl:value-of select="SUPPLIER_AID"/></td>
				<td><xsl:value-of select="ARTICLE_DETAILS/DESCRIPTION_SHORT"/></td>
				<td><xsl:value-of select="ARTICLE_DETAILS/EAN"/></td>
				<td><xsl:value-of select="ARTICLE_DETAILS/DESCRIPTION_LONG"/></td>
				<td><xsl:value-of select="ARTICLE_PRICE_DETAILS/ARTICLE_PRICE/@price_type"/></td>
				<td>
					<table>
						<xsl:apply-templates select="ARTICLE_PRICE_DETAILS/ARTICLE_PRICE"/>
					</table>
				</td>
			</tr>
	</xsl:template>
	
	<xsl:template match="ARTICLE_PRICE_DETAILS/ARTICLE_PRICE">
		<tr>
			<td><xsl:value-of select="PRICE_AMOUNT"/></td>
			<td><xsl:value-of select="TERRITORY"/></td>
			<td><xsl:value-of select="PRICE_CURRENCY"/></td>
			<td><xsl:value-of select="TAX"/></td>
		</tr>
	</xsl:template>
</xsl:stylesheet>
