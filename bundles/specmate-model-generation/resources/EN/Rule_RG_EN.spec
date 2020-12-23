import EN.DEP.STANFORD.*
import EN.POS.PTB.*
import EN.POS.LENA.*

def subtrees Limit, Conditional

def rule LimitedCondition_1 {
	[Limit] - nsubjpass -> [Conditional] - prep -> IN:'until'
}

// ex: Increase the counter until the counter reaches 100
// this should work for both active and passive because of action rules
def rule LimitedCondition_1_SPACY {
	[Conditional] - mark -> IN:'until'
	[Conditional] - advcl -> [Limit]
}

def rule LimitedCondition_2 {
	[Limit] - csubjpass -> [Conditional] - prep -> IN:'until'
}


def subtrees Cause, Effect, Head, TMP, Effect_Child, Cause_SubA, Cause_SubB, Cause_SubC, Cause_SubD

//  The button will display a spinning animation as soon as the user clicks the button.
def rule Condition1_0 {
	[Effect] - advmod -> RB:'soon':[TMP] - advmod -> RB:'as'
	[TMP] - advcl -> [Cause] - mark -> IN:'as'
}

def rule Condition1_1 {
	[Effect] - advcl -> [Cause]	- mark -> IN:'if'
	[Effect] - advmod -> RB: 'then'
}

def rule Condition1_2 {
	[Effect] - dep -> [Cause] - mark -> IN:'if'
	[Effect] - advmod -> RB: 'then'
}

def rule Condition1_3 {
	[Effect] - dobj -> [Cause] - mark -> IN:'if'
	[Effect] - advmod -> RB: 'then'
}

//  If/Because/Although the tool detects an error, the tool beeps.
//  The tool beeps if/because/although the tool detects an error.
def rule Condition1_4 {
	[Effect] - advcl -> [Cause] - mark -> IN:'(if)|(because)|(although)'
}

def rule Condition1_5 {
	[Effect] - dep -> [Cause] - mark -> IN:'(if)|(because)|(although)'
}

// When the tool detects an error then the tool beeps.
def rule Condition2_1 {
	[Cause] - ccomp -> [Effect]
	[Cause] - advmod -> WRB:'when'
	[Cause] - advmod -> RB: 'then'
}

def rule Condition2_1_SPACY {
	[Effect] - advcl -> [Cause] - advmod -> WRB:'when'
	[Effect] - advmod -> RB: 'then'
}

def rule Condition2_2 {
	[Cause] - dobj -> [Effect]
	[Cause] - advmod -> WRB:'when'
	[Cause] - advmod -> RB: 'then'
}

def rule Condition2_3 {
	[Cause] - advcl -> [Effect]
	[Cause] - advmod -> RB: 'then'
	[Cause] - advmod -> WRB:'when'
}


def rule Condition2_4 {
	[Cause] - ccomp -> [Effect]
	[Cause] - mark -> IN:'If'
}


def rule Condition2_5 {
	[Cause] - mark -> IN:'If'
	[Cause] - advmod -> RB:'then'
	[Cause] - dep -> [Effect]
}

def rule Condition2_6 {
	[Cause] - mark -> IN:'If'
	[Cause] - dobj -> [Cause_SubA] - prep -> [Cause_SubB] - pobj -> [Cause_SubC] - advmod -> RB:'then'
	[Cause_SubC] - dep -> [Effect]
}

def rule Condition2_7 {
	[Cause] - mark -> IN:'If'
	[Cause] - dobj -> [Cause_SubA] - dep -> [Cause_SubB] - dep -> [Cause_SubC] - prep -> [Cause_SubD]  - advmod -> RB:'then'
	[Cause_SubD] - dep -> [Effect]
}

// When the tool detects an error, the tool beeps.
def rule Condition2_8 {
	[Effect] - dep -> [Cause] - advmod -> WRB:'when'
}

// The tool beeps when the tool detects an error.
def rule Condition2_9 {
	[Effect] - advcl -> [Cause] - advmod -> WRB:'when'
}

def rule Condition2_10 {
	[Cause] - advmod -> WRB: 'when'
	[Cause] - parataxis -> [Effect]
}


// The tool detects an error and for this reason the tool beeps .
def rule Condition3_1 {
	[Cause] - conj -> [Effect]
	[Cause] - cc -> 'and'
	[Effect] - prep -> 'for' - pobj -> 'reason' - det -> 'this'
}

// The tool detects an error and as a result the tool beeps.
def rule Condition4_1 {
	[Cause] - advcl -> [Effect] - mark -> IN:'as'
	[Effect] - nsubj -> NN:'result' - det -> DT:'a'
}
def rule Condition4_2 {
	[Cause] - ccomp -> [Effect]
	[Cause] - prep -> IN:'as' - pobj -> NN:'result' -det -> DT:'a'
}

// Specmate shows the error window as a result of invalid login data.
def rule Condition4_3 {
	[Effect] - dobj -> [Effect_Child] - prep -> IN:'as' - pobj -> NN:'result':[TMP] - prep -> IN:'of' - pobj -> [Cause]
}

// Specmate shows the error window as a result of invalid login data.
def rule Condition4_4 {
	[Effect] - prep -> IN:'as' - pobj -> NN:'result':[TMP] - prep -> IN:'of' - pobj -> [Cause]
}
def rule Condition4_5 {
	[Cause] - prep -> IN:'as' - pobj -> NN:'result' -rcmod -> [Effect]
}

// The tool beeps due to the tool detecting an error.
def rule Condition5_1 {
	[Cause] - prep -> 'due' - pcomp -> TO:'to' - pobj -> [Effect]
}
// Due to the tool detecting an error, the tool beeps.
def rule Condition5_2 {
	JJ:'due':[TMP] - prep -> TO:'to' - pobj -> [Cause]
	[TMP] - dep -> [Effect]
}

// The tool beeps owning to the tool detecting an error.
def rule Condition6_1 {
	VBG:'owning':[TMP] - prep -> TO:'to' - pobj -> [Cause]
	[TMP] - nsubj -> [Effect]
}

// Owning to the tool detecting an error, the tool beeps.
def rule Condition6_2 {
	[Effect] - dep -> VBG:'owning':[TMP] - prep -> TO:'to' - pobj -> [Cause]
}

// The tool beeps provided/supposing that the tool detected an error.
def rule Condition7_1 {
	[Effect] - partmod -> (VBN:'provided'|VBG:'supposing') -ccomp -> [Cause] - complm -> IN:'that' 
}
	
// Specmate saves the model provided that the model is correct.
def rule Condition7_2 {
	[Effect] -ccomp -> (VBD:'provided'|VBG:'supposing') -ccomp -> [Cause] - complm -> IN:'that' 
	[Effect] -ccomp -> (VBD:'provided'|VBG:'supposing') -nsubj -> [Effect_Child]
}
// Supposing that the tool detected an error, the tool beeps .
def rule Condition7_3 {
	[Effect] -dep-> VBG:'supposing' -ccomp-> [Cause] -complm-> IN:'that'
}

// Provided that the tool detected an error, the tool beeps.
def rule Condition7_4 {
	VBN:'provided'  - ccomp -> [Cause] - dobj -> [Cause_SubA] -appos -> [Effect]
	[Cause] - complm -> IN:'that'
}

// Provided that the tool detected an error, the tool beeps.
// Provided that the tool crashed, the tool beeps.
def rule Condition7_5 {
	VBN:'provided'  - ccomp -> [Cause] - dobj -> [Effect]
	[Cause] - complm -> IN:'that'
}

// The tool beeping has something/(a lot) to do with it detecting an error.
def rule Condition8_1 {
	VBZ:*:[TMP] - nsubj -> [Effect]
	[TMP] - dobj -> NN:"(something)|lot" - infmod -> VB:"do" - prep -> IN:'with' - pobj -> [Cause]
}

// The tool detects an error so that it can report it.
def rule Condition9_1 {
	[Cause] - advcl -> [Effect] - dep -> IN:'that'
	[Effect] - advmod -> RB:'so'
}

// The tool detects an error so that it can report it.
def rule Condition9_2 {
	[Cause] - dobj -> [Effect] - dep -> IN:'that'
	[Effect] - advmod -> RB:'so'
}

// The tool detects an error so to report it.
def rule Condition9_3 {
	[Cause] - advmod -> RB:'so'
	[Cause] - xcomp -> [Effect] - aux -> TO:'to'
}


// The tool detects an error in order that it can report it. 
def rule Condition10_1 {
	[Cause] - prep -> IN:'in' - pobj -> NN:'order' - ccomp ->  [Effect] - complm -> IN:'that'
}
// In order that the tool can report an error, the tool detects errors. 
def rule Condition10_2 {
	[Cause] - prep -> IN:'in' - pobj -> NN:'order' 
	[Cause] - advcl ->  [Effect] - complm -> IN:'that'
}
// The tool detects an error in order to report it.
def rule Condition10_3 {
	[Cause] - advcl -> [Effect]
	[Effect] - mark -> IN:'in'
	[Effect] - dep -> NN:'order'
	[Effect] - aux -> TO:'to'
}

// The tool beeps even though the tool detected an error .
def rule Condition11_1 {
	[Cause] - nsubj -> [Effect]
	[Cause] - advmod -> RB:'even'
	[Cause] - dep -> RB:'though'
}
// Even though the tool detected an error, the tool beeps.
// Even though the tool crashed, the tool beeps.
def rule Condition11_2 {
	[Effect] - advcl -> [Cause] - advmod -> RB:'even'
	[Cause] - dep -> IN:'though'
}

// The tool beeps in case that the tool detected an error.
def rule Condition12_1 {
	[Effect] - ccomp -> [Cause] - complm -> IN:'that'
	[Effect] - prep -> IN:'in' - pobj -> NN:'case'
}

// In case that the tool detected an error, the tool beeps.
// In case that the tool crashes, the tool beeps.
def rule Condition12_2 {
	IN:'in':[TMP] - pobj -> NN:'case'
	[TMP] - dep -> IN:'that' - pobj -> [Cause] - appos -> [Effect]
}

// The tool beeps on the condition that the tool detected an error .
def rule Condition13_1 {
	[Effect] - ccomp -> [Cause] - complm -> IN:'that'
	[Effect] - prep -> IN:'on' - pobj -> NN:'condition'
}

// On the condition that the tool detected an error, the tool beeps.
// On the condition that the tool crashes, the tool beeps.
def rule Condition13_2 {
	IN:'on' - pobj -> NN:'condition' - prep -> IN:'that' - pobj -> [Cause] - appos -> [Effect]
}

def subtrees Parent, Child, Label, New, Old, TMP, TMP2, Label_Sub

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

def rule Composition_1 {
	noun:[Child] - prep -> IN:'(at)|(on)|(of)|(inside)|(in)':[Label] - pobj -> noun:[Parent]
}

def rule Composition_2 {
	noun:[Parent] - prep -> IN:'with':[Label] - pobj -> noun:[Child]
}

def rule Composition_3 {
	noun:[Parent] - acl -> 'showing':[Label] - pobj -> noun:[Child]
}

def rule Composition_4 {
	'has|have':[Label] - nsubj -> noun:[Parent]
	[Label] - dobj -> noun:[Child]
}
def rule Composition_5 {
	[Child] - prep -> IN:'from':[Label] - pobj -> noun:[Parent]
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
	[Label] - nsubj -> noun:[Parent]
	[Label] - dobj -> noun:[Child]
}

def rule Action_1 {
	[Label] - dobj -> noun:[Child]
}

def rule Action_Explicit_Prep_1 {
	verb:[Label] - prep -> [Label_Sub] - pobj -> noun:[Child]
	verb:[Label] - nsubj -> noun:[Parent]
}

def rule Action_Prep_1 {
	verb:[Label] - prep -> [Label_Sub] - pobj -> noun:[Child]
}

def rule Action_Passive_1 {
	verb:[Label] - nsubjpass -> noun:[Child]
}

def subtrees  PartA, PartB, Head, Head_tmp
def subtrees  PartA_Child, PartB_Parent

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
	[PartA] - ccomp -> [PartB]  - nsubj -> [PartA_Child] - conj -> [PartB_Parent]
	[PartA_Child] - cc -> CC:'or'
}

// the spinner or loading effect
// TODO id of TMP
def rule Conjunction_OR_3 {
	[TMP] - nmod -> [PartA] - conj -> [PartB]
	[PartA] - cc -> CC:'or'
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
// Specmate has the changes and Specmate opens a new window 
def rule Conjunction_AND_3 {
	[PartA] - ccomp -> [PartB]  - nsubj -> [PartA_Child] - conj -> [PartB_Parent]
	[PartA_Child] - cc -> CC:'and'
}

def rule Conjunction_AND_4 {
	[PartA] - dobj -> [PartA_Child]  - conj -> [PartB]
	[PartA_Child] - cc -> CC:'and'
}

def rule Action_Sub {
	[Label] - nsubj -> [Parent]
}

def rule Composition_Sub {
	'has|have':[Label] - nsubj -> [Parent]
}

def rule Inheritance_Sub {
	'is':[Label] - nsubj -> [Parent]
}

def rule Negation {
	[Head] - neg -> RB:*
}

def rule Negation_2 {
	[Head] - neg -> DT:*
}

def rule Negation_3 {
	[Head] - det -> DT:'no'
}



/*
TMP rules help in two ways:
- removing parts of the dependency tree that should not be node contents
- allowing parsing of rules that don't include the root verb (see MatchUtil)
*/

def rule TMP_1 {
	[PartA] - acl -> [TMP]
}
def rule TMP_2 {
	[PartA] - advmod -> [TMP]
}
def rule TMP_3 {
	[PartA] - advcl -> [PartB]
}
def rule TMP_4 {
	[PartA] - mark -> [TMP]
}
// ex: we want (TMP, root) to do (Head) something
def rule TMP_5 {
	[TMP] - xcomp -> [PartA]
	[PartA] - aux -> TO:'to'
}
// remove a, an, the
def rule TMP_6 {
	[PartA] - det -> [TMP]
}
// will do
def rule TMP_7 {
	[PartA] - aux -> MD:[TMP]
}
