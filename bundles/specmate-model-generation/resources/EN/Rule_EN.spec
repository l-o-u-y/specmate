import EN.DEP.STANFORD.*
// import EN.DEP.SPACY.*
import EN.POS.PTB.*
import EN.POS.LENA.*

def subtrees Source, Action, Target, Parent, Child, Label, New, Old, TMP, TMP2

// ex: we want a rectangle instead of a square
def rule Update_Replace_1_1 {
	[New] - cc -> IN:'of':[TMP] - advmod -> RB:'instead'
	[TMP] - pobj -> [Old]
}

// ex: instead of a square we want a rectangle, instead of an animation display a square
def rule Update_Replace_With_1_2 {
	[TMP] - dobj -> [New]
	[TMP] - prep -> IN:'of':[TMP2] - advmod -> RB:'instead'
	[TMP2] - pobj -> [Old]
}
// ex: display a rectangle instead of a square
def rule Update_Replace_With_Verb_1_3 {
	[TMP] - dobj -> [Old]
	[TMP] - prep -> IN:'of':[TMP2] - advmod -> RB:'instead'
	[TMP] - dobj -> [New]
}
// ex: display a rectangle instead of the spinning animation on the button
def rule Update_Replace_1_With_Verb_1_4 {
	[TMP] - dobj -> [Old]
	[TMP] - dobj -> [New]
	[TMP] - prep -> IN:'of':[TMP2] - advmod -> RB:'instead'
}

// ex: change the animation to white from green
def rule Update_Replace_2_1 {
	'(change)|(changes)|(changing)':[TMP] - prep -> IN:'to' - pobj -> [New]
	[New] - prep -> IN:'from' - pobj -> [Old]
}
// ex: change from green to white 
def rule Update_Replace_2_2 {
	'(change)|(changes)|(changing)':[TMP] - prep -> IN:'from':[TMP2] - pobj -> [Old]
	[TMP2] - prep -> IN:'to' - pobj -> [New]
}

// ex: change the animation to a rectangle
def rule Update_Replace_2_3 {
	'(change)|(changes)|(changing)':[TMP] - prep -> IN:'to' - pobj -> [New]
	[TMP] - dobj -> [Old]
}
// TODO MA ex: change the animation from green to white 

// ex: replacing the animation with a rectangle
def rule Update_Replace_3 {
	'(replace)|(replaces)|(replacing)':[TMP] - dobj -> [Old]
	[TMP] - prep -> IN:'with' - pobj -> [New]
}
// ex: replace the rectangle with a circle
def rule Update_Replace_3_2 {
	'(replace)|(replaces)|(replacing)':[TMP] - dobj -> [Old]  - prep -> IN:'with' - pobj -> [New]
}

def rule Update_Remove_1 {
	'(remove)|(removes)|(removing)' - dobj -> [Old]
}

def rule Composition_1_1 {
	noun:[Child] - prep -> IN:'(on)|(of)|(inside)|(in)':[Label] - pobj -> noun:[Parent]
}

def rule Composition_2 {
	noun:[Parent] - prep -> IN:'with':[Label] - pobj -> noun:[Child]
}

def rule Composition_3 {
	noun:[Parent] - acl -> 'showing':[Label] - pobj -> noun:[Child]
}

// ex: QBtn also has the material ripple effect baked in
/*def rule Composition_4_acl {
	'has':[Label] - nsubj -> noun:[Parent]
	[Label] - dobj -> noun:[Child] - acl -> [TMP]
}*/

def rule Composition_4 {
	'has':[Label] - nsubj -> noun:[Parent]
	[Label] - dobj -> noun:[Child]
}
def rule Composition_5 {
	[Child] - prep -> IN:'from' - pobj -> noun:[Parent]
}

def rule Inheritance_1 {
	'is':[Label] - nsubj -> noun:[Child]
	[Label] - attr -> noun:[Parent]
}

def rule Inheritance_1_2 {
	noun:[Child] - relcl -> 'is':[Label] - attr -> noun:[Parent]
}

def rule Inheritance_2 { // a component -> called -> qbtn
	noun:[Parent] - acl -> [Label] - oprd -> noun:[Child]
}

def rule Inheritance_Colon { // it comes in two shapes: green and blue
	noun:[Parent] - appos -> [Child]
}

def rule Action_Explicit_1 {
	verb:[Action] - nsubj -> noun:[Source]
	verb:[Action] - dobj -> noun:[Target]
}

def rule Action_1 {
	verb:[Action] - dobj -> noun:[Target]
}

def rule Action_Explicit_Prep_1 {
	verb:[Action] - prep -> [Label] - pobj -> noun:[Target]
	verb:[Action] - nsubj -> noun:[Source]
}

def rule Action_Prep_1 {
	verb:[Action] - prep -> [Label] - pobj -> noun:[Target]
}

def rule Action_Passive_1 {
	verb:[Action] - nsubjpass -> noun:[Target]
}


def rule TMP_1 {
	[Head] - acl -> [TMP]
}
def rule TMP_2 {
	[Head] - advmod -> [TMP]
}
def rule TMP_3 {
	[Head] - advcl -> [TMP]
}
def rule TMP_3 {
	[Head] - mark -> [TMP]
}

def subtrees  PartA, PartB, Head, Head_tmp
def subtrees  PartA_SubA, PartB_SubA

def rule Conjunction_NOR_1 {
	[PartA] - preconj -> CC:'neither'
	[PartA] - cc -> CC: 'nor'
	[PartA] - conj -> [PartB]
}

def rule Conjunction_NOR_2 {
	[PartA] - preconj -> CC:'neither'
	[PartA] - cc -> CC: 'nor'
	[PartA] - conj -> [PartB]
}

def rule Conjunction_XOR {
	[PartA] - advmod -> RB:'either'
	[PartA] - cc -> CC: 'or'
	[PartA] - conj -> [PartB]
}

def rule Conjunction_OR {
	[PartA] - cc -> CC:'or'
	[PartA] - conj -> [PartB]
}

def rule Conjunction_OR_2 {
	[PartA] - ccomp -> [PartB]  - nsubj -> [PartA_SubA] - conj -> [PartB_SubA]
	 [PartA_SubA] - cc -> CC:'or'
}

def rule Conjunction_AND_1 {
	[PartA] - cc -> CC:'and'
	[PartA] - conj -> [PartB]
	[PartA] - preconj -> DT:'both'
}

def rule Conjunction_AND_2 {
	[PartA] - cc -> CC:'and'
	[PartA] - conj -> [PartB]
}

def rule Conjunction_AND_3 {
	[PartA] - ccomp -> [PartB]  - nsubj -> [PartA_SubA] - conj -> [PartB_SubA]
	[PartA_SubA] - cc -> CC:'and'
}

def rule Conjunction_AND_4 {
	[PartA] - dobj -> [PartA_SubA]  - conj -> [PartB]
	[PartA_SubA] - cc -> CC:'and'
}

def rule Negation {
	[Head] - neg -> RB:*
}

def rule Negation_2 {
	[Head] - dobj -> [Head_tmp] - neg -> DT:*
}

def rule Negation_3 {
	[Head] - dobj-> [Head_tmp] - det -> DT:'no'
}
// TODO MA file handling for CEG/RG