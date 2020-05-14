package com.sgm.liteapp.cloudapi.dto;

import java.util.List;

public class LiteappPackageUrlDTO {

	private String currentVersion;

	private String fullPackageUrl;

	private List<UpdateChain> updateChain;

	public String getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getFullPackageUrl() {
		return fullPackageUrl;
	}

	public void setFullPackageUrl(String fullPackageUrl) {
		this.fullPackageUrl = fullPackageUrl;
	}

	public List<UpdateChain> getUpdateChain() {
		return updateChain;
	}

	public void setUpdateChain(List<UpdateChain> updateChain) {
		this.updateChain = updateChain;
	}

	public static class UpdateChain {

		private String fromVersion;

		private String toVersion;

		private String diffPackageUrl;

		public String getFromVersion() {
			return fromVersion;
		}

		public void setFromVersion(String fromVersion) {
			this.fromVersion = fromVersion;
		}

		public String getToVersion() {
			return toVersion;
		}

		public void setToVersion(String toVersion) {
			this.toVersion = toVersion;
		}

		public String getDiffPackageUrl() {
			return diffPackageUrl;
		}

		public void setDiffPackageUrl(String diffPackageUrl) {
			this.diffPackageUrl = diffPackageUrl;
		}

	}
}
