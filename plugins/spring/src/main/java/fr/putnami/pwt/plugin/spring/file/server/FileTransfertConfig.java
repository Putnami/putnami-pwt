/**
 * This file is part of pwt.
 *
 * pwt is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * pwt is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with pwt. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package fr.putnami.pwt.plugin.spring.file.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import fr.putnami.pwt.plugin.spring.file.server.controller.FileTransfertController;
import fr.putnami.pwt.plugin.spring.file.server.support.DefaultFileTransfertStore;
import fr.putnami.pwt.plugin.spring.file.server.support.FileTransfertStore;

@Configuration
public class FileTransfertConfig {

	@Bean
	public FileTransfertController fileTransfertController() {
		return new FileTransfertController();
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}

	@Bean
	public FileTransfertStore fileTransfertStore() {
		return new DefaultFileTransfertStore();
	}
}
