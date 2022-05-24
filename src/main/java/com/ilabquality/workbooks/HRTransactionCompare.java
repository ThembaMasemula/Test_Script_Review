package com.ilabquality.workbooks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ilabquality.dataprovider.XlsxDataProvider;

public class HRTransactionCompare {

	public static void main(String[] args) throws Exception {
		// XlsxDataProvider xlsxFile = new XlsxDataProvider(
		// System.getProperty("user.dir") + System.getProperty("file.separator") +
		// "workbooks",
		// "EC HR Transactions Configuration Workbook.xlsx", "2 - Event Derivation
		// Req.");
		// XlsxDataProvider xlsxFileTrue = new XlsxDataProvider(
		// System.getProperty("user.dir") + System.getProperty("file.separator") +
		// "workbooks",
		// "EC HR Transactions Configuration Workbook True.xlsx", "2 - Event Derivation
		// Req.");

		XlsxDataProvider xlsxFile = new XlsxDataProvider();
		xlsxFile.setXlsxFile(System.getProperty("user.dir") + System.getProperty("file.separator") + "workbooks"
				+ System.getProperty("file.separator") + "EC HR Transactions Configuration Workbook.xlsx");
		List<String> allSheets = xlsxFile.getAllSheetName();

		XlsxDataProvider xlsxFileTrue = new XlsxDataProvider();
		xlsxFileTrue.setXlsxFile(System.getProperty("user.dir") + System.getProperty("file.separator") + "workbooks"
				+ System.getProperty("file.separator") + "EC HR Transactions Configuration Workbook True.xlsx");
		List<String> allSheetsTrue = xlsxFile.getAllSheetName();

		if (!allSheetsTrue.equals(allSheets)) {
			System.out.println("Sheets arent the same");
		}

		allSheets.remove("Cover Sheet");
		allSheets.remove("Introduction");
		// allSheets.remove("1 - Event Reasons");
		// allSheets.remove("4 - Workflow Roles");

		for (String sheet : allSheets) {
			xlsxFile.setSheetName(sheet);
			xlsxFileTrue.setSheetName(sheet);
			System.out.println("Comparing sheet: " + sheet);
			compareXlsxNew(xlsxFileTrue, xlsxFile);
		}

		System.out.println("Compare finished");

	}

	public static void compareXlsxNew(XlsxDataProvider xlsxTrueDp, XlsxDataProvider xlsxDp) throws Exception {
		xlsxDp.setXlsxFile(System.getProperty("user.dir") + System.getProperty("file.separator") + "workbooks"
				+ System.getProperty("file.separator") + "EC HR Transactions Configuration Workbook.xlsx");
		xlsxTrueDp.setXlsxFile(System.getProperty("user.dir") + System.getProperty("file.separator") + "workbooks"
				+ System.getProperty("file.separator") + "EC HR Transactions Configuration Workbook True.xlsx");

		ArrayList<ArrayList<String>> xlsxTrue = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> xlsx = new ArrayList<ArrayList<String>>();
		xlsxTrue = xlsxTrueDp.readRowsOfWorkbook();
		xlsx = xlsxDp.readRowsOfWorkbook();

		if (xlsx.equals(xlsxTrue)) {
			System.out.println("The sheets are the same");
			// return true;
		} else {
			System.out.println("The sheets aren't the same");

			ArrayList<ArrayList<String>> notInTrue = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> notInClient = new ArrayList<ArrayList<String>>();

			notInTrue = removeDuplicates(xlsxTrue, xlsx);
			ArrayList<Integer> pos = xlsxDp.findRowsInWorkbook(notInTrue);

			// ArrayList<Integer> difPos = xlsxDp.findOneMoreDifferenceInLine(pos,
			// notInTrue, xlsxTrue);

			notInClient = removeDuplicates(xlsx, xlsxTrue);
			ArrayList<Integer> posTrue = xlsxTrueDp.findRowsInWorkbook(notInClient);

			//DIF ORDERS MATTERS
			ArrayList<ArrayList<Integer>> potential = new ArrayList<ArrayList<Integer>>();
			potential = findLine(notInTrue, notInClient);
			//DIF ORDERS MATTERS
			Integer[] numberOfDifs = new Integer[potential.size()];
			for (int i = 0; i < potential.size(); i++) {
				numberOfDifs[i] = potential.get(i).get(3) - potential.get(i).get(2);
				notInTrue.get(potential.get(i).get(0));
				notInTrue.get(potential.get(i).get(0));
			}

			List<Integer> notJumpInTrue = new ArrayList<Integer>();
			for (int i = 0; i < potential.size(); i++) {
				notJumpInTrue.add(potential.get(i).get(1));
			}

			ArrayList<String> cellsToHighlight = new ArrayList<String>();

			System.out.println("Diferences found at lines : " + pos);
			System.out.println();

			int size = notInTrue.size();
			for (int i = 0; i < notInTrue.size(); i++) { // erros client
				for (int j = 0; j < notInClient.size(); j++) { // erros baseline
					ArrayList<String> listWoDupl = removeDuplicatesNew(notInClient.get(j), notInTrue.get(i));
					// ArrayList<String> listTWoDupl = removeDuplicatesNew(notInTrue.get(i),
					// notInClient.get(j));
					if (notJumpInTrue.contains(i)) {
						if (listWoDupl.size() < size) {
							if (!listWoDupl.isEmpty()) {
								notInClient.remove(j);
								// notInTrue.remove(i); //SE DER RUIM APAGAR ISSO AQUI
								cellsToHighlight.addAll(listWoDupl);
								break;
							}
						}
					} else {
						notInClient.remove(j);
						notJumpInTrue.add(i);
						i--;
						// notInTrue.remove(i); //SE DER RUIM APAGAR ISSO AQUI
						break;
					}
				}
			}


			// highlight cell
			//BUT ORDER MATTERS!
			int sizePosOriginal = pos.size();
			int cellsToHighLightOriginal = cellsToHighlight.size();
			Boolean[] remove = new Boolean[cellsToHighlight.size()];
			ArrayList<Integer> removePos = new ArrayList<Integer>();
			int difs = 0;

			for (int po = 0; po < pos.size(); po++) {
				for (int j = 0; j < cellsToHighlight.size(); j++) {
					if (numberOfDifs[po] > 0) {
						Integer positionCell = xlsxDp.getCellColumnNumber(cellsToHighlight.get(j), pos.get(po));
						if (sizePosOriginal == cellsToHighLightOriginal) {
							if (!removePos.contains(pos.get(po))) {
								removePos.add(pos.get(po));
								remove[j] = true;
							}
							xlsxDp.highlighCell(pos.get(po), positionCell);
							numberOfDifs[po] = numberOfDifs[po] - 1;
							break;
						} else if (positionCell != null && positionCell < 40) {
							// System.out.println(cellsToHighlight.get(j) + " " + positionCell);
							remove[j] = true;
							if (!removePos.contains(pos.get(po))) {
								removePos.add(pos.get(po));
							}
							xlsxDp.highlighCell(pos.get(po), positionCell);
							numberOfDifs[po] = numberOfDifs[po] - 1;
						}
						// } else {
						// break;
					}

				}
				for (int r = 0; r < remove.length; r++) {
					if (remove[r] != null && remove[r]) {
						cellsToHighlight.remove(r);
						remove = Arrays.stream(remove).skip(1).toArray(Boolean[]::new);
						r--;
					} else {
						break;
					}
				}
				// difs++;
			}

			for (Integer rp : removePos) {
				pos.remove(rp);
			}

			for (Integer rp : removePos) {
				posTrue.remove(rp);
			}

			for (Integer p : pos) {
				xlsxDp.highlightLine(p);
			}



			for (Integer p : posTrue) {
				System.out.println("Row " + p + " does not exists in Client Workbook");
				System.out.println("Content of row " + p + " is : " + xlsxTrue.get(p - 1));
			}

			System.out.println();

		}

		// return false;

	}

	private static ArrayList<ArrayList<Integer>> findLine(ArrayList<ArrayList<String>> xlsxOne,
			ArrayList<ArrayList<String>> xlsxTwo) {
		ArrayList<ArrayList<String>> xOne = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> xTwo = new ArrayList<ArrayList<String>>();
		xOne.addAll(xlsxOne);
		xTwo.addAll(xlsxTwo);
		int aux = 0;
		int cells = 0;
		int auxCells = 0;
		int ja = 0;
		int matches = 0;
		int blanks = 0;
		ArrayList<Integer> lineOne = new ArrayList<Integer>();
		ArrayList<Integer> lineTwo = new ArrayList<Integer>();
		ArrayList<Integer> nmbrOfMatches = new ArrayList<Integer>();
		ArrayList<Integer> nmbrOfCells = new ArrayList<Integer>();

		ArrayList<Integer> potentialLines = new ArrayList<Integer>();

		for (int i = 0; i < xOne.size(); i++) {
			for (int j = 0; j < xOne.get(i).size(); j++) {
				if (xOne.get(i).get(j).isEmpty() || xOne.get(i).get(j).trim().equals("")) {
					xOne.get(i).remove(j);
					j--;
				}
			}
		}
		for (int i = 0; i < xTwo.size(); i++) {
			for (int j = 0; j < xTwo.get(i).size(); j++) {
				if (xTwo.get(i).get(j).isEmpty() || xTwo.get(i).get(j).trim().equals("")) {
					xTwo.get(i).remove(j);
					j--;
				}
			}
		}
		/*
		 * for (ArrayList<String> one : xlsxOne) { for (String s : one) { if(s.isEmpty()
		 * || s.equals("")) { xlsxOne.remove(s); } } }
		 */
		ArrayList<ArrayList<Integer>> toma = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < xOne.size(); i++) {
			for (int j = 0; j < xTwo.size(); j++) {
				for (int j2 = 0; j2 < xOne.get(i).size(); j2++) {
					for (int k = 0; k < xTwo.get(j).size(); k++) {
						if (!xOne.get(i).get(j2).toString().equals("")) {
							auxCells = k;
							if(xOne.get(i).size() == xTwo.get(j).size()) {
								if (xOne.get(i).get(j2).equals(xTwo.get(j).get(k))) {
									aux++;
									break;
								}
							} else {
								break;
							}
						}

					}
				}

				ja = j;
				matches = aux;
				cells = auxCells;
				auxCells = 0;
				aux = 0;
				if (toma.size() == i) {
					lineOne.add(i);
					lineOne.add(ja);
					lineOne.add(matches);
					lineOne.add(cells + 1);
					toma.add(lineOne);
					lineOne = new ArrayList<Integer>();
				}
				for (int k = i; k < toma.size(); k++) {
				if (toma.get(k).get(0) != i) {
					lineOne.add(i);
					lineOne.add(ja);
					lineOne.add(matches);
					lineOne.add(cells + 1);
					toma.add(lineOne);
					lineOne = new ArrayList<Integer>();
				} else {
					//for (int j2 = 0; j2 < toma.size(); j2++) {
						//ArrayList<Integer> arrAux = new ArrayList<Integer>();
						//arrAux = toma.get(k);
						//if (toma.get(k).get(0) == i) {
							//if (toma.get(j2).get(1) == ja) {
								if (toma.get(k).get(2) < matches) {
									toma.remove(k);
									lineOne.add(i);
									lineOne.add(ja);
									lineOne.add(matches);
									lineOne.add(cells + 1);
									toma.add(lineOne);
									lineOne = new ArrayList<Integer>();
								}
							//}
						//}

					//}
				}
				}
				/*
				 * if (matches > xTwo.size() / 3 && matches < xTwo.size()) { lineOne.add(i);
				 * lineOne.add(ja); lineOne.add(matches); lineOne.add(cells + 1);
				 * toma.add(lineOne); lineOne = new ArrayList<Integer>(); // lineTwo.add(ja); //
				 * nmbrOfMatches.add(matches); // nmbrOfCells.add(cells + 1); }
				 */
			}

		}
		// ArrayList<ArrayList<Integer>> toma = new ArrayList<ArrayList<Integer>>();
		// toma.add(lineOne);
		// toma.add(lineTwo);
		// toma.add(nmbrOfMatches);
		// toma.add(nmbrOfCells);
		return toma;
		// potential = getBestPotencials(lineOne, lineTwo, nmbrOfMatches);

		// return potential;
	}

	@SuppressWarnings("unused")
	private static Map<Integer, Integer[]> getBestPotencials(ArrayList<Integer> lineOne, ArrayList<Integer> lineTwo,
			ArrayList<Integer> nmbrOfMatches) {
		List<Integer> one = new ArrayList<Integer>();
		List<Integer> bigests = new ArrayList<Integer>();
		Map<Integer, Integer[]> potential = new HashMap<Integer, Integer[]>();
		// List<Integer> potential = new ArrayList<Integer>();
		for (int i = 0; i < lineOne.size() - 1; i++) {
			if (lineOne.get(i).equals(lineOne.get(i + 1))) {
				one.add(i);
			} else {
				for (int j = 0; j < one.size(); j++) {
					bigests.add(nmbrOfMatches.get(one.get(j)));
				}
				Integer max = Collections.max(bigests, null);
				Integer two = null;
				for (int j = 0; j < bigests.size(); j++) {
					if (bigests.get(j) == max) {
						two = j;
						bigests = new ArrayList<Integer>();
						break;
					}
				}
				potential.put(lineOne.get(i), new Integer[] { two, max });
			}
		}

		return potential;
	}

	public static ArrayList<ArrayList<String>> removeDuplicates(ArrayList<ArrayList<String>> base,
			ArrayList<ArrayList<String>> removed) {
		ArrayList<ArrayList<String>> baseNew = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> otherNew = new ArrayList<ArrayList<String>>();
		baseNew.addAll(base);
		otherNew.addAll(removed);
		for (ArrayList<String> lineBase : baseNew) {
			for (ArrayList<String> line : otherNew) {
				if (line.equals(lineBase)) {
					otherNew.remove(line);
					break;
				}
			}

		}
		return otherNew;
	}

	public static ArrayList<String> removeDuplicatesNew(ArrayList<String> base, ArrayList<String> other) {
		ArrayList<String> baseNew = new ArrayList<String>();
		ArrayList<String> otherNew = new ArrayList<String>();
		baseNew.addAll(base);
		otherNew.addAll(other);
		for (String lineBase : baseNew) {
			for (String line : otherNew) {
				if (line.equals(lineBase)) {
					otherNew.remove(line);
					break;
				}
			}
		}
		return otherNew;
	}

	public static String[] concat(String[] arrA, String[] arrB) {
		String[] result = new String[arrA.length + arrB.length];
		for (int i = 0; i < arrA.length; i++) {
			result[i] = arrA[i].trim();
		}

		for (int i = arrA.length; i < arrB.length; i++) {
			result[i] = arrB[i].trim();
		}

		return result;
	}

	public static String[] clearDuplicates(String[] array) {
		Set<String> set = new HashSet<String>();
		for (String num : array) {
			set.add(num);
		}
		String[] arryWithoutDuplicates = set.toArray(new String[set.size()]);
		return arryWithoutDuplicates;
	}

	public static String[] treatedVariable(String variable) {

		variable.substring(1, variable.length() - 1).trim();
		return variable.split("\n");
	}

	public static ArrayList<String> convertArrayToArrayList(String[] array) {
		ArrayList<String> arrayList = new ArrayList<String>();

		for (int i = 0; i < array.length; i++) {
			arrayList.add(array[i]);
		}
		return arrayList;
	}

	public static String[] unsetDifferentsValueArray(String[] arrayTrue, String[] array) {
		String[] newArray = new String[arrayTrue.length];

		for (String row : arrayTrue) {
			for (int i = 0; i < newArray.length; i++) {
				if (row.equals(array[i])) {
					// System.out.println(row + " ------------> " + array[i]);
					newArray[i] = array[i];
				}
			}

		}
		return newArray;
	}

	public static String[] getDifferentsValueArray(String[] arrayTrue, String[] array) {
		String[] newArray = new String[arrayTrue.length];

		for (String row : arrayTrue) {
			for (int i = 0; i < newArray.length; i++) {
				if (row.equals(array[i])) {
					System.out.println(row + " ------------> " + array[i]);
					newArray[i] = array[i];
				}
			}

		}
		return newArray;
	}

	public static boolean compareXlsx(XlsxDataProvider xlsxTrueDp, XlsxDataProvider xlsxDp) throws Exception {
		String xlsxTrue = xlsxTrueDp.readRowByRow();
		String xlsx = xlsxDp.readRowByRow();
		int rowPosition;

		String[] positionWrong;
		String[] rowsXlsxTrue = treatedVariable(xlsxTrue);
		String[] rowsXlsx = treatedVariable(xlsx);
		String[] cell;
		String[] cellTrue;
		String[] teste;

		Integer[] changeBackgroundCell;

		if (xlsx.equals(xlsxTrue)) {
			System.out.println("The sheets are the same");
			return true;
		} else {
			System.out.println("The sheets aren't the same");
			for (int i = 0; i < rowsXlsxTrue.length; i++) {
				rowPosition = i + 1;

				for (String row : rowsXlsx) {
					cell = rowsXlsx[i].split(" - ");
					cellTrue = rowsXlsxTrue[i].split(" - ");
					if (!row.equals(rowsXlsxTrue[i])) {
						for (int j = 0; j < cellTrue.length; j++) {
							if (!cell[j].equals(cellTrue[j])) {
								positionWrong = xlsxDp.getCellPosition(cell[j]);
								System.out.println("The colunm " + positionWrong[1] + " in row " + rowPosition
										+ " are differents from the baseline");
								changeBackgroundCell = xlsxDp.getCellPositionNumber(cell[j]);
								xlsxDp.changeBackgroundCell(rowPosition, changeBackgroundCell[1]);
							}
						}
						break;
					}
				}
			}
			return false;
		}
	}
	// =============================================================

}
