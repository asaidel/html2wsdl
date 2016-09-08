package html2wsdl.vo.parameters;

public class OffsetParameters {
	
	private int initialRow, 
	headerSize, 
	rowSize,
	initialRowResponse,
	rowSizeResponse,
	initialCol;
	
	/**
	 * @param initialRow
	 * @param headerSize
	 * @param rowSize
	 * @param initialRowResponse
	 * @param rowSizeResponse
	 * @param initialCol
	 */
	public OffsetParameters(int initialRow, int headerSize, int rowSize, int initialRowResponse, int rowSizeResponse,
			int initialCol) {
		super();
		this.initialRow = initialRow;
		this.headerSize = headerSize;
		this.rowSize = rowSize;
		this.initialRowResponse = initialRowResponse;
		this.rowSizeResponse = rowSizeResponse;
		this.initialCol = initialCol;
	}

	
	public int getInitialRow() {
		return initialRow;
	}

	public void setInitialRow(int initialRow) {
		this.initialRow = initialRow;
	}

	public int getHeaderSize() {
		return headerSize;
	}

	public void setHeaderSize(int headerSize) {
		this.headerSize = headerSize;
	}

	public int getRowSize() {
		return rowSize;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public int getInitialRowResponse() {
		return initialRowResponse;
	}

	public void setInitialRowResponse(int initialRowResponse) {
		this.initialRowResponse = initialRowResponse;
	}

	public int getRowSizeResponse() {
		return rowSizeResponse;
	}

	public void setRowSizeResponse(int rowSizeResponse) {
		this.rowSizeResponse = rowSizeResponse;
	}

	public int getInitialCol() {
		return initialCol;
	}

	public void setInitialCol(int initialCol) {
		this.initialCol = initialCol;
	}
}
