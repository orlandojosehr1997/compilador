<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">

	<xsl:template match="/">
		<html>

			<head>
				<style type="text/css">
					table.tfmt {
					border: 1px ;
					}

					td.colfmt {
					border: 1px ;
					background-color: white;
					color: black;
					text-align:right;
					}

					th {
					background-color: #2E9AFE;
					color: white;
					}

				</style>
			</head>

			<body>
				<table class="tfmt">
					<tr>
						<th style="width:250px">Token</th>
						<th style="width:350px">Tipo Token</th>
						<th style="width:250px">Apariciones</th>


					</tr>

					<xsl:for-each select="Scanner/Tokens/Token">

						<tr>
							<td class="colfmt">
								<xsl:value-of select="Nombre" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="Tipo" />
							</td>

							<td class="colfmt">
								<xsl:value-of select="Apariciones" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>