package de.diddiz.LogBlock;

public class LookupCacheElementFactoryFactory
{
	public LookupCacheElementFactory create(QueryParams params, float spaceFactor) {
		return new LookupCacheElementFactory(params, spaceFactor);
	}
}
