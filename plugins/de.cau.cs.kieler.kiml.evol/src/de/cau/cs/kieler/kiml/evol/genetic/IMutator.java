package de.cau.cs.kieler.kiml.evol.genetic;

/**
 * Interface for a factory that creates mutated genes.
 *
 * @author bdu
 *
 */
public interface IMutator {
    /**
     *
     * @param template
     *            the template; may not be {@code null}
     * @param mutationInfo
     *            the mutation info; may not be {@code null}
     * @return a new gene
     */
    IGene<Float> newMutation(final UniversalNumberGene template, final MutationInfo mutationInfo);
}