package com.sensei.search.req;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.search.SortField;

import com.browseengine.bobo.api.BrowseSelection;
import com.browseengine.bobo.api.FacetSpec;

public class SenseiRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HashMap<String,BrowseSelection> _selections;
	private ArrayList<SortField> _sortSpecs;
	private Map<String,FacetSpec> _facetSpecMap;
	private String _query;
	private int _offset;
	private int _count;
	private boolean _fetchStoredFields;
	private int[] _filteredIDs;
	private final Map<String,FacetHandlerInitializerParam> _facetInitParamMap;
	private int[] _partitions;
	
	public SenseiRequest(){
		_facetInitParamMap = new HashMap<String,FacetHandlerInitializerParam>();
		_selections=new HashMap<String,BrowseSelection>();
		_sortSpecs=new ArrayList<SortField>();
		_facetSpecMap=new HashMap<String,FacetSpec>();
		_filteredIDs = null;
		_fetchStoredFields = false;
		_partitions = null;
	}
	
	public void setPartitions(int[] partitions){
		_partitions = partitions;
	}
	
	public int[] getPartitions(){
		return _partitions;
	}
	
	public Map<String,FacetHandlerInitializerParam> getFacetHandlerInitParamMap(){
		return _facetInitParamMap;
	}
	
	public void putAllFacetHandlerInitializerParams(Map<String,FacetHandlerInitializerParam> params){
		_facetInitParamMap.putAll(params);
	}
	
	public void setFacetHandlerInitializerParam(String name,FacetHandlerInitializerParam param){
		_facetInitParamMap.put(name, param);
	}
	
	public FacetHandlerInitializerParam getFacetHandlerInitializerParam(String name){
		return _facetInitParamMap.get(name);
	}
	
	public Map<String,FacetHandlerInitializerParam> getAllFacetHandlerInitializerParams(){
		return _facetInitParamMap;
	}
	
	public Set<String> getSelectionNames(){
		return _selections.keySet();
	}
	
	public void removeSelection(String name){
		_selections.remove(name);
	}
	
	public void setFacetSpecs(Map<String,FacetSpec> facetSpecMap)
	{
		_facetSpecMap = facetSpecMap;
	}
	
	public Map<String,FacetSpec> getFacetSpecs()
	{
		return _facetSpecMap;
	}
	
	
	public int getSelectionCount()
	{
		return _selections.size();
	}
	
	/**
	 * Set a default filter
	 * @param filter
	 */
	public void setFilterIDs(int[] filterIDs){
		_filteredIDs = filterIDs;
	}
	
	/**
	 * Gets the default filter
	 */
	public int[] getFilterIDs(){
		return _filteredIDs;
	}
	
	public void clearSelections(){
		_selections.clear();
	}
	
	/**
	 * Gets the number of facet specs
	 * @return number of facet pecs
	 * @see #setFacetSpec(String, FacetSpec)
	 * @see #getFacetSpec(String)
	 */
	public int getFacetSpecCount(){
		return _facetSpecMap.size();
	}
	
	public void clearSort(){
		_sortSpecs.clear();
	}
	
	public boolean isFetchStoredFields(){
		return _fetchStoredFields;
	}
	
	public void setFetchStoredFields(boolean fetchStoredFields){
		_fetchStoredFields = fetchStoredFields;
	}
	
	/**
	 * Sets a facet spec
	 * @param name field name
	 * @param facetSpec Facet spec
	 * @see #getFacetSpec(String)
	 */
	public void setFacetSpec(String name,FacetSpec facetSpec){
		_facetSpecMap.put(name,facetSpec);
	}
	
	/**
	 * Gets a facet spec
	 * @param name field name
	 * @return facet spec
	 * @see #setFacetSpec(String, FacetSpec)
	 */
	public FacetSpec getFacetSpec(String name){
		return _facetSpecMap.get(name);
	}
	
	/**
	 * Gets the number of hits to return. Part of the paging parameters.
	 * @return number of hits to return.
	 * @see #setCount(int)
	 */
	public int getCount() {
		return _count;
	}

	/**
	 * Sets the number of hits to return. Part of the paging parameters.
	 * @param count number of hits to return.
	 * @see #getCount()
	 */
	public void setCount(int count) {
		_count = count;
	}

	/**
	 * Gets the offset. Part of the paging parameters.
	 * @return offset
	 * @see #setOffset(int)
	 */
	public int getOffset() {
		return _offset;
	}

	/**
	 * Sets of the offset. Part of the paging parameters.
	 * @param offset offset
	 * @see #getOffset()
	 */
	public void setOffset(int offset) {
		_offset = offset;
	}

	/**
	 * Set the search query
	 * @param query lucene search query
	 * @see #getQuery()
	 */
	public void setQuery(String query){
		_query=query;
	}
	
	/**
	 * Gets the search query
	 * @return lucene search query
	 * @see #setQuery(String)
	 */
	public String getQuery(){
		return _query;
	}
	
	/**
	 * Adds a browse selection
	 * @param sel selection
	 * @see #getSelections()
	 */
	public void addSelection(BrowseSelection sel){
		String[] vals = sel.getValues();
		if (vals==null || vals.length == 0)
		{
			String[] notVals = sel.getNotValues();
			if (notVals==null || notVals.length == 0) return;		// skip adding useless selections
		}
		_selections.put(sel.getFieldName(),sel);
	}
	
	/**
	 * Gets all added browse selections
	 * @return added selections
	 * @see #addSelection(BrowseSelection)
	 */
	public BrowseSelection[] getSelections(){
		return _selections.values().toArray(new BrowseSelection[_selections.size()]);
	}
	
	/**
	 * Gets selection by field name
	 * @param fieldname
	 * @return selection on the field
	 */
	public BrowseSelection getSelection(String fieldname){
	  return _selections.get(fieldname);
	}
	
	/**
	 * Add a sort spec
	 * @param sortSpec sort spec
	 * @see #getSort() 
	 * @see #setSort(SortField[])
	 */
	public void addSortField(SortField sortSpec){
		_sortSpecs.add(sortSpec);
	}

	/**
	 * Gets the sort criteria
	 * @return sort criteria
	 * @see #setSort(SortField[])
	 * @see #addSortField(SortField)
	 */
	public SortField[] getSort(){
		return _sortSpecs.toArray(new SortField[_sortSpecs.size()]);
	}
	
	/**
	 * Sets the sort criteria
	 * @param sorts sort criteria
	 * @see #addSortField(SortField)
	 * @see #getSort()
	 */
	public void setSort(SortField[] sorts){
		_sortSpecs.clear();
		for (int i=0;i<sorts.length;++i){
			_sortSpecs.add(sorts[i]);
		}
	}
	
	@Override
	public String toString(){
	  StringBuilder buf=new StringBuilder();
      buf.append("query: ").append(_query).append('\n');
      buf.append("page: [").append(_offset).append(',').append(_count).append("]\n");
      buf.append("sort spec: ").append(_sortSpecs).append('\n');
      buf.append("selections: ").append(_selections).append('\n');
      buf.append("facet spec: ").append(_facetSpecMap).append('\n');
      buf.append("fetch stored fields: ").append(_fetchStoredFields);
      return buf.toString();
	}
}
